import {Squad} from "@app/view/fire-overview/models/fire.model";

export class FireAcceptModel {
  firePlace: String;
  latitude: number;
  longitude: number;
  squads: Squad[];
}
