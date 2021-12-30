package com.nortal.platformer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    public static void main(String[] args) {
        Game game = new Game("src/main/resources/platforms.csv");
        game.run();
    }

    private Integer points = 500;
    private final String gameFile;

    private Platform activePlatform;
    private Platform[] platforms;
    private List<Platform> unlockedPlatforms = new ArrayList<Platform>();
    private Integer[] grindingSpotValues;

    public Game(String gameFile) {
        this.gameFile = gameFile;
    }

    public void run() {
        try {
            this.platforms = readPlatforms();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Platform firstPlatform = platforms[0];
        this.unlockedPlatforms.add(firstPlatform);
        this.activePlatform = firstPlatform;
        System.out.println(String.format("\nPlatform costs: %s",
                Stream.of(platforms).map(p -> p.getCost())
                        .collect(Collectors.toList())));

        this.grindingSpotValues = getGrindingSpots(platforms);
        System.out.println(String.format("Grinding spot values: %s\n",
                Arrays.toString(grindingSpotValues)));
        // I assume that there will be enough points to unlock the next platform
        // since otherwise there will be no way to get more points
        jumpTo(platforms[1]);

        moveToTheBestGrindingSpot();
        moveToDestination(platforms.length - 1);
    }

    /**
     * Invoke this function to jump to next platform.
     * 
     * @param platform - Platform that you are going to jump to.
     */
    public void jumpTo(Platform platform) {
        Integer activePlatIndex = activePlatform.getIndex();
        Integer platIndex = platform.getIndex();
        Integer platCost = platform.getCost();

        if (activePlatIndex == platIndex) // ! Inferred rule
            throw new Error("Can not jump to the same platform that you're on");
        else if (Math.abs(activePlatIndex - platIndex) > 1)
            /*
             * ! This rule is inferred --- not stated in the game rules.
             * If I'd be brave enough I'd check if there's enough points to unlock
             * the last platform and go there, claiming to have finished
             * the game with 1 jump.
             */
            throw new Error("Can not jump over platforms");

        if (!unlockedPlatforms.contains(platform)) {
            if (platCost > points)
                throw new Error("Not enough points to unlock the new platform");
            unlockedPlatforms.add(platform);
            this.points -= platCost;
        } else
            this.points += platCost;

        System.out.println(String.format("Jump from [%d] => [%d]\nPoints at the moment: [%d]\n",
                activePlatIndex, platIndex, points));
        this.activePlatform = platform;
    }

    // #region Grinding
    /**
     * Gets gets the sum of platform cost and of the platform ahead of it. <br/>
     * Used to determine if it's more viable to grind points at an unlocked spot
     * or to move forwards.
     * 
     * @param platforms - List of platforms
     * @return Points that can be grinded by going forwards and backwards
     *         on the grinding spots
     */
    private Integer[] getGrindingSpots(Platform[] platforms) {
        List<Integer> grindingSpots = new ArrayList<Integer>();
        // "-1" makes it not include last platform as a grindable spot -- ends the game
        Integer grindablePlatforms = platforms.length - 1;

        for (int i = 0; i < grindablePlatforms - 1; i++) {
            Integer plat1Cost = platforms[i].getCost();
            Integer plat2Cost = platforms[i + 1].getCost();

            Integer grindingSpot = plat1Cost + plat2Cost;
            grindingSpots.add(grindingSpot);
        }
        return grindingSpots.toArray(new Integer[0]);
    }

    /**
     * Gets the index of the next grinding spot that is better than the current
     * 
     * @param currentGrindingSpot - the grinding spot from where a better spot
     *                            should be found
     * @return Index of the next grinding spot.
     *         Returns current grinding spot if there isn't a better one.
     */
    private Integer getNextGrindingSpot(Integer currentGrindingSpot) {
        Integer currentGrindSpotValue = grindingSpotValues[currentGrindingSpot];
        Integer newAreaStart = currentGrindingSpot + 1;
        Integer[] grindingSpotsAhead = Arrays.copyOfRange(grindingSpotValues,
                newAreaStart, grindingSpotValues.length);

        for (int i = 0; i < grindingSpotsAhead.length; i++) {
            Integer grindingSpotValue = grindingSpotsAhead[i];
            if (grindingSpotValue < currentGrindSpotValue)
                continue;
            Integer nextSpot = newAreaStart + i;
            return nextSpot;
        }
        return currentGrindingSpot;
    }

    /**
     * Grinds points by jumping back and forth in a grinding spot
     * 
     * @param pointsNeededToGrind - how many points are neede to grind
     * @param grindingSpotStart   - starting index of the grinding spot
     * @return Points that can be grinded by going forwards and backwards
     *         on the grinding spots
     */
    private void grindForPoints(Integer pointsNeededToGrind, Integer grindingSpotStart) {
        Integer initialPlatIndex = activePlatform.getIndex();
        Boolean forwards = initialPlatIndex == grindingSpotStart;
        if (!forwards && Math.abs(initialPlatIndex - grindingSpotStart) > 1) {
            throw new Error("Too far from the grinding spot");
        }

        while (pointsNeededToGrind > points) {
            Integer nextPlatformIndex = activePlatform.getIndex() +
                    (forwards ? 1 : -1);
            jumpTo(unlockedPlatforms.get(nextPlatformIndex));
            forwards = !forwards;
        }
    }

    /**
     * Gets the current grinding spot
     * 
     * @return The index of the current grinding spot
     */
    private Integer getCurrentGrindingSpot() {
        Integer activePlatIndex = activePlatform.getIndex();
        Integer latestUnlockedPlatform = unlockedPlatforms.size() - 1;
        /*
         * Should be fine since there is a bias of moving forwards --
         * so if "activePlatform.index != latestUnlockedPlatform" there is no
         * need to check if "activePlatform.index - 1" would be a better
         * grinding spot or not
         */
        Integer currentGrindingSpot = activePlatIndex == latestUnlockedPlatform
                ? activePlatIndex - 1
                : activePlatIndex;
        return currentGrindingSpot;
    }
    // #endregion

    // #region Traversing
    /**
     * Gets the index of the next grinding spot that is better than the current
     * 
     * @param start       - index of the starting platform
     * @param destination - index of the destination platform
     * @return Points required to reach the destination. Negative
     */
    private Integer pointsRequiredToReachPlatform(Integer start, Integer destination) {
        Integer accumulatedPoints = 0;
        // Last unlocked platfrom at which point forwards points must be spent
        Integer lastUnlocked = unlockedPlatforms.size() - 1;
        if (start > destination) {
            Integer tempV = start;
            start = destination;
            destination = tempV;
        }

        for (int i = start; i < lastUnlocked; i++) {
            accumulatedPoints -= platforms[i].getCost();
        }
        for (int i = lastUnlocked + 1; i < destination + 1; i++) {
            accumulatedPoints += platforms[i].getCost();
        }
        return accumulatedPoints;
    }

    /**
     * Moves onto the desired destination.
     * 
     * @param destination - The index of the destination **platform**
     * @return Filled out Platform
     */
    private void moveToDestination(Integer destination) {
        Integer pointsToGrind = pointsRequiredToReachPlatform(
                activePlatform.getIndex(), destination);
        if (points < pointsToGrind)
            grindForPoints(pointsToGrind, getCurrentGrindingSpot());
        for (int i = activePlatform.getIndex() + 1; i < destination + 1; i++) {
            jumpTo(platforms[i]);
        }
    }

    /**
     * Gets to the best grinding spot
     * 
     * @return The index of the best grinding spot
     */
    private Integer moveToTheBestGrindingSpot() {
        Integer currentGrindingSpot = getCurrentGrindingSpot();
        Integer nextGrindingSpot = getNextGrindingSpot(currentGrindingSpot);

        while (currentGrindingSpot != nextGrindingSpot) {
            Integer nextGrindingSpotEnd = nextGrindingSpot + 1;
            moveToDestination(nextGrindingSpotEnd);

            currentGrindingSpot = nextGrindingSpot;
            nextGrindingSpot = getNextGrindingSpot(currentGrindingSpot);
        }
        return currentGrindingSpot;
    }
    // #endregion

    // #region ReadingFromCSV
    /**
     * Reads a platform from scanner
     * 
     * @param columns     - CSV file scanner
     * @param columnOrder - what data corresponds to what column in CSV file
     * @return Filled out Platform
     */
    private Platform readPlatform(String[] columns, Dictionary<String, Integer> columnOrder) {
        // Platform platform = new Platform();
        String indexColumn = columns[columnOrder.get("index")].trim();
        String costColumn = columns[columnOrder.get("cost")].trim();

        Integer index = Integer.parseInt(indexColumn);
        Integer cost = Integer.parseInt(costColumn);

        Platform platform = Platform.builder()
                .index(index).cost(cost)
                .build();
        return platform;
    }

    /**
     * Reads platforms from CSV file and returns it as a list.
     * 
     * @return An Array of Platforms
     * @throws IOException
     */
    private Platform[] readPlatforms() throws IOException {
        String DELIMITER = ",";
        List<Platform> platforms = new ArrayList<Platform>();

        Dictionary<String, Integer> columnOrder = new Hashtable<String, Integer>();
        BufferedReader br = Files.newBufferedReader(Paths.get(gameFile));
        // #region Parsing_Header
        String line = br.readLine();
        String[] headers = line.split(DELIMITER);

        // Gets which column corresponds with which sort of data (cost or index)
        for (int i = 0; i < headers.length; i++) {
            columnOrder.put(headers[i].trim(), i);
        }
        // #endregion

        // Parsing rest of the file
        while ((line = br.readLine()) != null) {
            String[] columns = line.split(DELIMITER);
            Platform platform = readPlatform(columns, columnOrder);
            platforms.add(platform);
        }

        // Dealing with the possibility that indexes in CSV file are out of order
        // since they are explicitly defined.
        // platforms.sort(new Comparator<Platform>() {
        //     @Override
        //     public int compare(Platform p1, Platform p2) {
        //         return p1.getIndex().compareTo(p2.getIndex());
        //     }
        // });
        /*
         * Unnecessary due to the 3rd Game Rule:
         * Each line contains one platform. First platform is always indexed as 0
         * and platform indexes grow by one each row.
         */

        // Converting to an array since size mutability is no longer a concern
        return platforms.toArray(new Platform[0]);
    }
    // #endregion
}
