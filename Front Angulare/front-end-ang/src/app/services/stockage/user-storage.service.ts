import { Injectable } from '@angular/core';

const TOKEN = 'stir-token';
const USER = 'stir-user';

@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() { }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN);
    window.sessionStorage.setItem(TOKEN, token);
  }

  public saveUser(user): void {
    window.sessionStorage.removeItem(USER);
    window.sessionStorage.setItem(USER, JSON.stringify(user));
  }

  static getToken(): string {
    return sessionStorage.getItem(TOKEN);
  }

  static getUser(): any {
    return JSON.parse(sessionStorage.getItem(USER));
  }

  static getUserId(): any {
    const user = this.getUser();
    if (user == null) {
      return '';
    }
    return user.userId;
  }

  static getUserRole(): any {
    const user = this.getUser();
    if (user == null) {
      return '';
    }
    return user.role;
  }

  static isAdminLoggedIn(): boolean {
    if (this.getToken() == null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role == 'ADMIN';
  }

  static isClientLoggedIn(): boolean {
    if (this.getToken() == null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role == 'CLIENT';
  }

  static signout(): void {
    window.sessionStorage.removeItem(TOKEN);
    window.sessionStorage.removeItem(USER);
  }
}
