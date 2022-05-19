##### WINTER UNIVERSITY 2022

# FULLSTACK EXERCISE

## Watt a City

Nortal has agreed to build IT infrastructure for brand new Mega City. The city is going to be the next world
wonder. For city to be smart, a lot of programming must be done. Your team’s first task is to build system for
managing Mega City’’s energy needs. This system is going to control how much energy will be forked from
main power grid to different buildings in the city. Poor engineering of the system might result in complete
meltdown of this marvelous city, so make sure to bring your A game. Once you are completed programming
the system it must be faster than Usain Bolt and more robust than Nokia 3310. Break a leg.

### Setup

#### Java Application

If on windows execute “gradlew.bat build” in project root folder. If on unix or linux execute “gradlew build”. You
can also use your own locally installed gradle, but it might result in mismatching gradle versions.

Alternatively, you can skip these previous steps by installing smart IDE like IntelliJ which does forementioned
automatically.

#### Angular Application

To start the application, you need to Node.js version 14+. You can download it from here
https://nodejs.org/en/download/.

Run “npm install” in frontend folder. After it has finished you can execute “npm run start” to run angular
application.

### Assignment

Your teammate Juku started implementing the application, but it still needs a lot of work. He left you three
user stories, which describe changes and improvements you need to make. There are also some TODOs in
the codebase, but don not solely rely on them.

#### Story 1: Finish update building implementation

Navigate to [http://localhost:4200/building/1.](http://localhost:4200/building/1.) This view should be finished so that when clicked on “Save”
button building is updated.

###### Add building index validation - Index must start with NO. For example, following are valid indexes - NO123,

NOEE20. If index is invalid display message “Index has to start with NO” below the input field.

###### Add building energy unit validation - Energy units are not allowed to exceed maximum energy units. If

they do display message “This building can stake a maximum of X units.”

###### Disable editing of sector code and energy unit max - Disable sector code field out. Do not let users

modify either of these values. Both values are assigned during initial saving and won’t be updatable later.

#### Story 2: Implement create building view

Implement view for creating new buildings. This view should be located at [http://localhost:4200/building/new.](http://localhost:4200/building/new.)
This view should be identical to edit building view with two following exceptions:

###### Enable sector code field - This field should be enabled only during initial save.

###### Add maximum energy unit field - This field should be visible only during initial save.

#### Story 3: Improvements to building list view

Building list view is located at [http://localhost:4200/buildings.](http://localhost:4200/buildings.)

###### Add update building button – Add this button to every table row. When clicked navigate user to building

editing view.

###### Add create building button – Add this button above the table. When clicked navigate user to create new

building view.


