import { BrigadeModel } from '@app/view/fire-overview/models/brigade.model';

export class FireModel {
  id: number;
  postalCode: string;
  city: string;
  street: string;
  date: Date;
  status: Status;
  squads: Squad[];
}

export class Squad {
  brigade: BrigadeModel;
  squadAmount: number;
}

export enum Status {
  ACTIVE = 'ACTIVE',
  INACTIVE = 'INACTIVE'
}
