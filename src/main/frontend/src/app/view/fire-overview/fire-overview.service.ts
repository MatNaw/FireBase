import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { first } from 'rxjs/operators';

import { FireModel } from '@app/view/fire-overview/models/fire.model';

@Injectable()
export class FireOverviewService {

  private static readonly URL_PREFIX: string = 'fire';

  constructor(private httpClient: HttpClient) { }

  getAllActiveFires(): Observable<FireModel[]> {
    return this.httpClient.get<FireModel[]>(`${FireOverviewService.URL_PREFIX}/active`)
      .pipe(first());
  }

  reportFire(): Observable<any> {
    // todo: report fire
    console.log('add request to BE');
    return of();
  }
}
