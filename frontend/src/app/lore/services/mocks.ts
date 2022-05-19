import { Building } from '../model/building';
import { Human } from '../model/human';

export const BUILDING_MOCKS: Building[] = [
	{
		id: '1',
		name: 'Building 1',
		address: 'Address 1',
		index: '12341',
		sectorCode: 'ABC121',
		energyUnits: 1000,
		energyUnitMax: 1500,
	},
	{
		id: '2',
		name: 'Building 2',
		address: 'Address 2',
		index: '12342',
		sectorCode: 'ABC122',
		energyUnits: 1500,
		energyUnitMax: 2500,
	},
	{
		id: '3',
		name: 'Building 3',
		address: 'Address 3',
		index: '12343',
		sectorCode: 'ABC123',
		energyUnits: 500,
		energyUnitMax: 500,
	},
];

export const HUMAN_MOCKS: Human[] = [ 
	{
		id: '1',
		name: 'Human 1',
		livesin: 'Address 1',
		gender: 'male/female',
		age: 10,
	},
	{
		id: '2',
		name: 'Human 2',
		livesin: 'Address 2',
		gender: 'male/female',
		age: 20,
	},
	{
		id: '3',
		name: 'Human 3',
		livesin: 'Address 3',
		gender: 'male/female',
		age: 30,
	},
];