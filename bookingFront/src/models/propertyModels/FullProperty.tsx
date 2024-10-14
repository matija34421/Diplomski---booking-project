import UserForOtherEntities from "../usermodels/UserForOtherEntities";

type FullProperty = {
    id: number,
    title:string,
    description:string,
    type:string,
    landlord:UserForOtherEntities,
    picture_path:string,
    price:number,
    location:string,
	addres:string,
    petFriendly:boolean,
	wiFi:boolean,
	kitchen:boolean,
	breakfast:boolean,
	lunch:boolean,
	dinner:boolean,
	ac:boolean,
	smokingAllowed:boolean,
	tv:boolean,
	freeCancelation:boolean,
	guestNumber:number,
	coordinateLat:string, 
	coordinateLng:string
}

export default FullProperty;