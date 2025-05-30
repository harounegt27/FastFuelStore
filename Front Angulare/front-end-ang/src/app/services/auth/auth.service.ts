import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { UserStorageService } from '../stockage/user-storage.service';


const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http:HttpClient,
    private userStorageService:UserStorageService,) { }

  register(signupRequest:any) : Observable<any>{
    return this.http.post(BASIC_URL + "sign-up" , signupRequest);
  }

  login(username : string , password : string): any {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const body = {username,password};

    return this.http.post(BASIC_URL + 'authentification', body, {headers, observe: 'response'}).pipe(
      map((res) => {
        const token = res.headers.get('Authorization').substring(6);
        const user = res.body;

        if(token && user){
          this.userStorageService.saveToken(token);
          this.userStorageService.saveUser(user);
          return true;
        }
        return false;
      })
    )
  }
}
