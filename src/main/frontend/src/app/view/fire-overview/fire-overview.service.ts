import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { first } from 'rxjs/operators';

import { FireModel, Squad } from '@app/view/fire-overview/models/fire.model';
import { SquadModel } from '@app/view/fire-overview/models/squad.model';
import { FireAcceptModel } from "@app/view/fire-overview/models/fire.accept.model";

@Injectable()
export class FireOverviewService {

  private static readonly URL_PREFIX: string = 'fire';

  constructor(private httpClient: HttpClient) { }

  getAllActiveFires(): Observable<FireModel[]> {
    return this.httpClient.get<FireModel[]>(`${FireOverviewService.URL_PREFIX}/active`)
      .pipe(first());
  }

  reportFire(latitude: number, longitude: number): Observable<SquadModel[]> {
    return this.httpClient.get<SquadModel[]>(`${FireOverviewService.URL_PREFIX}/report`,
      {
        params: {
          latitude: latitude.toString(),
          longitude: longitude.toString(),
        }
      })
      .pipe(first());
  }

  acceptFire(firePlace: String, latitude: number, longitude: number, squads: Squad[]): Observable<FireAcceptModel> {
    return this.httpClient.post<FireAcceptModel>(`${FireOverviewService.URL_PREFIX}/accept`,
      {
        firePlace, latitude, longitude, squads
      });
  }

  cancelFire(fireId: number): Observable<any> {
    console.log("cancel fire with id: " + fireId);
    return null;
  }
}
