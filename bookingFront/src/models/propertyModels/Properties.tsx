import UserForOtherEntities from "../usermodels/UserForOtherEntities";

type Property = {
    id: number,
    title:string,
    description:string,
    type:string,
    landlord:UserForOtherEntities,
    picture_path:string,
    price:number,
    location:string,
    address:string
}

export default Property;