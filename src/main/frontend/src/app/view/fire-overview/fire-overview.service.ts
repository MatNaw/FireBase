import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { first } from 'rxjs/operators';

import { FireModel } from '@app/view/fire-overview/models/fire.model';
import { SquadModel } from '@app/view/fire-overview/models/squad.model';

@Injectable()
export class FireOverviewService {

  private static readonly URL_PREFIX: string = 'fire';
  REPORT_FIRE_PARAMS = {
    latitude: '',
    longitude: '',
    brigadesNumber: ''
  };

  constructor(private httpClient: HttpClient) { }

  getAllActiveFires(): Observable<FireModel[]> {
    return this.httpClient.get<FireModel[]>(`${FireOverviewService.URL_PREFIX}/active`)
      .pipe(first());
  }

  reportFire(): Observable<SquadModel[]> {
    return this.httpClient.get<SquadModel[]>(`${FireOverviewService.URL_PREFIX}/report`,
      {
        params: this.REPORT_FIRE_PARAMS
      })
      .pipe(first());
  }

  acceptFire(): Observable<any> {
    // todo: accept fire
    console.log('accept fire');
    return of();
  }
}
