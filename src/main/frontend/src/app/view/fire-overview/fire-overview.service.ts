import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { first } from 'rxjs/operators';

import { FireModel } from '@app/view/fire-overview/models/fire.model';
import { SquadModel } from '@app/view/fire-overview/models/squad.model';
import { BrigadeModel } from '@app/view/fire-overview/models/brigade.model';

@Injectable()
export class FireOverviewService {

  private static readonly URL_PREFIX: string = 'fire';

  constructor(private httpClient: HttpClient) { }

  getAllActiveFires(): Observable<FireModel[]> {
    return this.httpClient.get<FireModel[]>(`${FireOverviewService.URL_PREFIX}/active`)
      .pipe(first());
  }

  reportFire(): Observable<SquadModel[]> {
    // todo: report fire
    console.log('add request to BE (report fire)');
    return of([
      {
        brigade: {
          id: 1,
          name: 'name 1',
          postalCode: '01-111',
          city: 'city 1111111 111111111 111111111',
          street: 'street 1',
        } as BrigadeModel,
        squadAmount: 2
      } as SquadModel,
      {
        brigade: {
          id: 2,
          name: 'name 2',
          postalCode: '02-222',
          city: 'city 2',
          street: 'street 2',
        } as BrigadeModel,
        squadAmount: 1
      } as SquadModel,
      {
        brigade: {
          id: 3,
          name: 'name 3',
          postalCode: '03-333',
          city: 'city 3',
          street: 'street 3',
        } as BrigadeModel,
        squadAmount: 2
      } as SquadModel,
      {
        brigade: {
          id: 4,
          name: 'name 4',
          postalCode: '04-444',
          city: 'city 4',
          street: 'street 4',
        } as BrigadeModel,
        squadAmount: 0
      } as SquadModel,
      {
        brigade: {
          id: 5,
          name: 'name 5',
          postalCode: '05-555',
          city: 'city 5',
          street: 'street 5',
        } as BrigadeModel,
        squadAmount: 0
      } as SquadModel,
      {
        brigade: {
          id: 6,
          name: 'name 6',
          postalCode: '06-666',
          city: 'city 6',
          street: 'street 6',
        } as BrigadeModel,
        squadAmount: 0
      } as SquadModel,
      {
        brigade: {
          id: 7,
          name: 'name 7',
          postalCode: '07-777',
          city: 'city 7',
          street: 'street 7',
        } as BrigadeModel,
        squadAmount: 0
      } as SquadModel,
      {
        brigade: {
          id: 8,
          name: 'name 8',
          postalCode: '08-888',
          city: 'city 8',
          street: 'street 8',
        } as BrigadeModel,
        squadAmount: 0
      } as SquadModel,
      {
        brigade: {
          id: 9,
          name: 'name 9',
          postalCode: '09-999',
          city: 'city 9',
          street: 'street 9',
        } as BrigadeModel,
        squadAmount: 0
      } as SquadModel,
      {
        brigade: {
          id: 10,
          name: 'name 10',
          postalCode: '10-111',
          city: 'city 10',
          street: 'street 10',
        } as BrigadeModel,
        squadAmount: 0
      } as SquadModel
    ]);
  }

  acceptFire(): Observable<any> {
    // todo: accept fire
    console.log('add request to BE (accept fire)');
    return of();
  }
}
