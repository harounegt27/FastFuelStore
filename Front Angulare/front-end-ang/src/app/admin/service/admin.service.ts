import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/services/stockage/user-storage.service';

const BASIC_URL = "http://localhost:8080/"

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  //Categorie API
  addCategorie(categorieDto:any): Observable<any>{
    return this.http.post(BASIC_URL + 'api/admin/categorie' ,categorieDto,{
      headers : this.createAuthorizationHeader()
    });
  }
  allCategorie(): Observable<any>{
    return this.http.get(BASIC_URL + 'api/admin/tout-categorie', {
      headers : this.createAuthorizationHeader()
    });
  }
  deleteCategorie(categorieId:any): Observable<any>{
    return this.http.delete(BASIC_URL + `api/admin/supp-categorie/${categorieId}` ,{
      headers : this.createAuthorizationHeader()
    });
  }
  updateCategorie(categorieId:any,categorieDto:any): Observable<any>{
    return this.http.put(BASIC_URL + `api/admin/upd-categorie/${categorieId}` ,categorieDto,{
      headers : this.createAuthorizationHeader()
    });
  }
  getCategorieById(categorieId:any): Observable<any>{
    return this.http.get(BASIC_URL + `api/admin/categorie/${categorieId}` ,{
      headers : this.createAuthorizationHeader()
    });
  }




  //PrixUnitaire API
  addPrixUnitaire(prixUnitaireDto:any): Observable<any >{
    return this.http.post(BASIC_URL + 'api/admin/prix-unit' ,prixUnitaireDto,{
      headers : this.createAuthorizationHeader()
    });
  }
  allPrixUnitaire(): Observable<any>{
    return this.http.get(BASIC_URL + 'api/admin/tout-prix',{
      headers : this.createAuthorizationHeader()
    });
  }
  getPrixUnitairesByProduitId(produitId: number): Observable<any|null>{
    return this.http.get(BASIC_URL + `api/admin/prodprix/${produitId}`,{
      headers : this.createAuthorizationHeader()
    });
  }
  getPrixById(prixId:any): Observable<any>{
    return this.http.get(BASIC_URL + `api/admin/prix-unit/${prixId}` ,{
      headers : this.createAuthorizationHeader()
    });
  }
  deletePrix(prixId:any): Observable<any>{
    return this.http.delete(BASIC_URL + `api/admin/prix-unit/${prixId}` ,{
      headers : this.createAuthorizationHeader()
    });
  }
  updatePrix(prixId:any,prixDto:any): Observable<any>{
    return this.http.put(BASIC_URL + `api/admin/prix-unit/${prixId}` ,prixDto,{
      headers : this.createAuthorizationHeader()
    });
  }



  //Produit API
  addPoduit(produitDto:any): Observable<any>{
    return this.http.post(BASIC_URL + 'api/admin/produit' ,produitDto,{
      headers : this.createAuthorizationHeader()
    });
  }
  allProduit(): Observable<any>{
    return this.http.get(BASIC_URL + 'api/admin/produits',{
      headers : this.createAuthorizationHeader()
    });
  }
  allProduitPU(): Observable<any>{
    return this.http.get(BASIC_URL + 'api/admin/produitsPU',{
      headers : this.createAuthorizationHeader()
    });
  }
  allProduitByName(name:any): Observable<any>{
    return this.http.get(BASIC_URL + `api/admin/recherche/${name}`,{
      headers : this.createAuthorizationHeader()
    });
  }
  deletePoduit(produitId:any): Observable<any>{
    return this.http.delete(BASIC_URL + `api/admin/produit/${produitId}` ,{
      headers : this.createAuthorizationHeader()
    });
  }
  updatePoduit(produitId:any,produitDto:any): Observable<any>{
    return this.http.put(BASIC_URL + `api/admin/produit/${produitId}` ,produitDto,{
      headers : this.createAuthorizationHeader()
    });
  }
  getPoduitById(produitId:any): Observable<any>{
    return this.http.get(BASIC_URL + `api/admin/produit/${produitId}` ,{
      headers : this.createAuthorizationHeader()
    });
  }




  //Voix API
  allVoix(): Observable<any>{
    return this.http.get(BASIC_URL + 'api/admin/voix', {
      headers : this.createAuthorizationHeader()
    });
  }




   //Stock API
  addStock(stockDto:any): Observable<any>{
    return this.http.post(BASIC_URL + 'api/admin/stock' ,stockDto,{
      headers : this.createAuthorizationHeader()
    });
  }
  allStock(): Observable<any>{
    return this.http.get(BASIC_URL + 'api/admin/stocks',{
      headers : this.createAuthorizationHeader()
    });
  }
  deleteStock(stockId:any): Observable<any>{
    return this.http.delete(BASIC_URL + `api/admin/stock/${stockId}` ,{
      headers : this.createAuthorizationHeader()
    });
  }
  getStockById(stockId:any): Observable<any>{
    return this.http.get(BASIC_URL + `api/admin/stock/${stockId}` ,{
      headers : this.createAuthorizationHeader()
    });
  }
  updateStock(stockId:any,stockDto:any): Observable<any>{
    return this.http.put(BASIC_URL + `api/admin/stock/${stockId}` ,stockDto,{
      headers : this.createAuthorizationHeader()
    });
  }


  //User API
  allUsers(): Observable<any>{
    return this.http.get(BASIC_URL + 'api/admin/users',{
      headers : this.createAuthorizationHeader()
    });
  }
  deleteUser(userId:any): Observable<any>{
    return this.http.delete(BASIC_URL + `api/admin/user/${userId}` ,{
      headers : this.createAuthorizationHeader()
    });
  }
  getUserById(userId:any): Observable<any>{
    return this.http.get(BASIC_URL + `api/admin/user/${userId}` ,{
      headers : this.createAuthorizationHeader()
    });
  }


  //Commande API
  allCommandes(): Observable<any>{
    return this.http.get(BASIC_URL + 'api/admin/commandes',{
      headers : this.createAuthorizationHeader()
    });
  }
  changeCommandeStatue(commandeId:any,statue:string): Observable<any>{
    return this.http.get(BASIC_URL + `api/admin/commande/${commandeId}/${statue}`,{
      headers : this.createAuthorizationHeader()
    });
  }
  deleteCommande(commandeId:any): Observable<any>{
    return this.http.delete(BASIC_URL + `api/admin/supp-commande/${commandeId}` ,{
      headers : this.createAuthorizationHeader()
    });
  }
  getCommandeById(commandeId:any): Observable<any>{
    return this.http.get(BASIC_URL + `api/admin/commande/${commandeId}`,{
      headers : this.createAuthorizationHeader()
    });
  }
  getCommandeProduits(commandeId:any): Observable<any>{
    return this.http.get(BASIC_URL + `api/admin/produits-commander/${commandeId}`, {
      headers : this.createAuthorizationHeader()
    });
  }




  //Messagerie API
  sendEmail(to: string, subject: string, messageBody: string): Observable<any> {
    const emailRequest = {
      to: to,
      subject: subject,
      messageBody: messageBody
    };
    return this.http.post(BASIC_URL + 'api/send-email', emailRequest);
  }

  private createAuthorizationHeader():HttpHeaders{
    const token = UserStorageService.getToken();
    return new HttpHeaders().set(
      'Authorization','EGTBNJ'+  token
    );
  }
}
