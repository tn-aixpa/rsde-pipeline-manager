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

export interface Elaboration {
  id: number;
  name: string;
  localName: string;
  status: string;
  createdAt: string;
  updatedAt: string;
}

@Injectable({
  providedIn: 'root'
})
export class ElaborationService {

  constructor(public httpClient: HttpClient) { }

  getElaborationList(page: number = 0, size: number = 10): Observable<PaginatedData<Elaboration>> {
    return this.httpClient.get<PaginatedData<Elaboration>>(`${environment.SERVER_URL}/api/elaborations?page=${page}&size=${size}`);
  }
}
