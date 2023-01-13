import { Injectable } from '@angular/core';

@Injectable()
export class AuthService {
  public isLoggedIn: Boolean;
  public accessToken: string = "";

  constructor() {
    this.isLoggedIn = false;
  }

  setLoggedIn(token: string) {
    this.isLoggedIn = true;
    this.accessToken = token;
    localStorage.setItem('accessToken', JSON.stringify(this.accessToken));
  }

  getLoggedIn() {
    let storageToken = localStorage.getItem('accessToken');
    console.log(storageToken);
    
    let data = {
      'token': 'Bearer 12132131asdasda'
    };

    return JSON.parse(JSON.stringify(data));
  }

  logout() {
    localStorage.removeItem('accessToken');
    this.isLoggedIn = false;
  }
}