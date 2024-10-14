import PropertyForOtherEntities from "../propertyModels/PropertyForOtherEntities";
import UserForOtherEntities from "../usermodels/UserForOtherEntities";

type Reservation = {
    id:number,
    end_date: string,
    start_date: string,
    user:UserForOtherEntities,
    property:PropertyForOtherEntities,
}

export default Reservation;
