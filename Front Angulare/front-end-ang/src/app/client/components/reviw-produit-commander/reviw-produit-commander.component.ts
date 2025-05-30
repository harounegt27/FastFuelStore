import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../../services/client.service';
import { UserStorageService } from 'src/app/services/stockage/user-storage.service';

@Component({
  selector: 'app-reviw-produit-commander',
  templateUrl: './reviw-produit-commander.component.html',
  styleUrls: ['./reviw-produit-commander.component.css']
})
export class ReviwProduitCommanderComponent {


  produitId : number = this.activatedRoute.snapshot.params["produitId"];
  reviewForm : FormGroup;

  constructor(
    private clientService: ClientService,
    private fb : FormBuilder,
    private snacks : MatSnackBar,
    private router : Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(){
    this.reviewForm = this.fb.group({
      rating : [null,[Validators.required]],
      description : [null,[Validators.required]]
    })
  }

  submitForm(){
    const formData : FormData = new FormData();
    formData.append('produitId', this.produitId.toString());
    formData.append('userId', UserStorageService.getUserId().toString());
    formData.append('rating', this.reviewForm.get('rating').value);
    formData.append('description', this.reviewForm.get('description').value);

    this.clientService.giveReview(formData).subscribe(res => {

        this.snacks.open('Ton avis a été enregitrer ! Merci pour votre feedback','Fermer',{ duration : 5000});
        this.router.navigateByUrl('client/tout-commandes')

    })
  }

}
