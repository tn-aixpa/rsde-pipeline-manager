import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

export interface PaginatedData<T> {
  content: Array<T>;
  number: number;
  size?: number;
  totalPages?: number;
  totalElements?: number;
  numberOfElements?: number;
  empty?: boolean;
  last?: boolean;
  first?: boolean;
}

export class Elaboration {
  constructor(
    public id?: number, 
    public name?: string, 
    public localName?: string, 
    public status?: string, 
    public createdAt?: string, 
    public updatedAt?: string,
    public parameters?: any,
    public tag?: string,
  ) { }
}

@Injectable({
  providedIn: 'root'
})
export class ElaborationService {

  constructor(public httpClient: HttpClient) { }

  getElaborationList(page: number = 0, size: number = 10): Observable<PaginatedData<Elaboration>> {
    return this.httpClient.get<PaginatedData<Elaboration>>(`${environment.SERVER_URL}/api/elaborations?page=${page}&size=${size}&sort=createdAt,desc`);
  }

    getElaboration(id: string): Observable<Elaboration> {
    return this.httpClient.get<Elaboration>(`${environment.SERVER_URL}/api/elaborations/${id}`);
  }

  deleteElaboration(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${environment.SERVER_URL}/api/elaborations/${id}`);
  }

  createElaboration(elaboration: Elaboration): Observable<Elaboration> {
    return this.httpClient.post<Elaboration>(`${environment.SERVER_URL}/api/elaborations`, elaboration);
  }

}
