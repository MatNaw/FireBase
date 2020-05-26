import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { first } from 'rxjs/operators';

import { FireModel } from '@app/view/fire-overview/models/fire.model';
import { SquadModel } from '@app/view/fire-overview/models/squad.model';

@Injectable()
export class FireOverviewService {

  private static readonly URL_PREFIX: string = 'fire';

  constructor(private httpClient: HttpClient) { }

  getAllActiveFires(): Observable<FireModel[]> {
    return this.httpClient.get<FireModel[]>(`${FireOverviewService.URL_PREFIX}/active`)
      .pipe(first());
  }

  reportFire(latitude, longitude, brigadesNumber): Observable<SquadModel[]> {
    return this.httpClient.get<SquadModel[]>(`${FireOverviewService.URL_PREFIX}/report`,
      {
        params: {
          latitude: latitude.toString(),
          longitude: longitude.toString(),
          brigadesNumber: brigadesNumber.toString(),
        }
      })
      .pipe(first());
  }

  acceptFire(): Observable<any> {
    // todo: accept fire
    console.log('accept fire');
    return of();
  }
}
