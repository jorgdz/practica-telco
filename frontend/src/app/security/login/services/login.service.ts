import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Api } from '../../../app.backend';
import { Observable } from 'rxjs';
import { User } from 'src/app/shared/models/user.account';

@Injectable()
export class LoginService {
    
  public api: string = Api;

  constructor(private http: HttpClient) {}

  login(user: User): Observable<any> {
    return this.http.post(this.api + '/api/auth/login', user, {
      observe: 'response',
    });
  }
}