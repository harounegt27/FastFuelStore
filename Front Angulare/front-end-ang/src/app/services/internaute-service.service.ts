import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class InternauteServiceService {

  constructor(private http: HttpClient) { }

  allProduit(): Observable<any>{
    return this.http.get(BASIC_URL + 'api/client/produits',{
    });
  }
  getPrixUnitairesByProduitId(produitId: number): Observable<any|null>{
    return this.http.get(BASIC_URL + `api/client/prodprix/${produitId}`,{
    });
  }
  allProduitByName(name:any): Observable<any>{
    return this.http.get(BASIC_URL + `api/client/recherche/${name}`,{
    });
  }
}
