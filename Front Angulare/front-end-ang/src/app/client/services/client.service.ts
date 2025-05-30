import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/services/stockage/user-storage.service';

const BASIC_URL = "http://localhost:8080/"
@Injectable({
  providedIn: 'root'
})



export class ClientService {

  constructor(private http: HttpClient) { }

  //Produit API
  allProduit(): Observable<any>{
    return this.http.get(BASIC_URL + 'api/client/produits',{
      headers : this.createAuthorizationHeader()
    });
  }
  allProduitByName(name:any): Observable<any>{
    return this.http.get(BASIC_URL + `api/client/recherche/${name}`,{
      headers : this.createAuthorizationHeader()
    });
  }



  //Wishlist API
  addProduitToWishlist(produitId: any): Observable<any> {
      const wishlistDto = {
        produitId: produitId,
        userId: UserStorageService.getUserId()
      }
      return this.http.post(BASIC_URL + 'api/client/addwishlist', wishlistDto , {
        headers: this.createAuthorizationHeader()
      });
  }
  getWishlistByUser(): Observable<any>{
    const userId = UserStorageService.getUserId();
    return this.http.get(BASIC_URL + `api/client/wishlist/${userId}`, {
      headers : this.createAuthorizationHeader()
    });
  }
  deleteProd(id:any): Observable<any>{
    return this.http.delete(BASIC_URL + `api/client/wishlist/${id}` ,{
      headers : this.createAuthorizationHeader()
    });
  }



  //Prix Unitaire API
  getPrixUnitairesByProduitId(produitId: number): Observable<any|null>{
    return this.http.get(BASIC_URL + `api/client/prodprix/${produitId}`,{
      headers : this.createAuthorizationHeader()
    });
  }







  //Panier API
  AddProduitToCart(produitId:any,voixId:any): Observable<any>{
    const cartDto = {
      produitId : produitId,
      voixId : voixId,
      userId : UserStorageService.getUserId()
    }
    return this.http.post(BASIC_URL + 'api/client/cart', cartDto , {
      headers : this.createAuthorizationHeader()
    });
  }
  getCartByUser(): Observable<any>{
    const userId = UserStorageService.getUserId();
    return this.http.get(BASIC_URL + `api/client/cart/${userId}`, {
      headers : this.createAuthorizationHeader()
    });
  }
  updateQuantity(produitId: any, newQte: any): Observable<any> {
    const updateDto = {
      produitId: produitId,
      userId: UserStorageService.getUserId(),
      newQte: newQte
    };
    return this.http.post(BASIC_URL + 'api/client/cart/QTEUpdate', updateDto, {
      headers: this.createAuthorizationHeader()
    });
  }
  deleteItemFromCart(produitId: any): Observable<any> {
    const userId = UserStorageService.getUserId();
    return this.http.delete(BASIC_URL + `api/client/cart/${userId}/item/${produitId}`, {
      headers: this.createAuthorizationHeader()
    });
  }




  //Commande API
  passerCommande(commandeDto:any): Observable<any>{
    commandeDto.userId = UserStorageService.getUserId();
    return this.http.post(BASIC_URL + `api/client/passerCommande`, commandeDto, {
      headers : this.createAuthorizationHeader()
    });
  }
  getCommandeByUser(): Observable<any>{
    const userId = UserStorageService.getUserId();
    return this.http.get(BASIC_URL + `api/client/commandes/${userId}`, {
      headers : this.createAuthorizationHeader()
    });
  }
  getCommandeProduits(commandeId:any): Observable<any>{
    return this.http.get(BASIC_URL + `api/client/produits-commander/${commandeId}`, {
      headers : this.createAuthorizationHeader()
    });
  }



  //Review API
  giveReview(reviewDto:any): Observable<any>{
    return this.http.post(BASIC_URL + `api/client/review`, reviewDto, {
      headers : this.createAuthorizationHeader()
    });
  }







  //User API
  getUserName(userId:any): Observable<any>{
    return this.http.get(BASIC_URL + `api/client/username/${userId}`, {
      headers : this.createAuthorizationHeader()
    });
  }

  updateUser(userDto:any): Observable<any>{
    const userId = UserStorageService.getUserId();
    return this.http.put(BASIC_URL + `api/client/userupdate/${userId}` ,userDto,{
      headers : this.createAuthorizationHeader()
    });
  }
  deleteUser(userId:any): Observable<any>{
    return this.http.delete(BASIC_URL + `api/client/user/${userId}` ,{
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


  //TOKEN API
  private createAuthorizationHeader():HttpHeaders{
    const token = UserStorageService.getToken();
    return new HttpHeaders().set(
      'Authorization','EGTBNJ'+  token
    );
  }
}
