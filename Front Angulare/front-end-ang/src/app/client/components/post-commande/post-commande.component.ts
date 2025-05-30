import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DialogService } from 'src/app/services/Dialog/dialog.service';
import { ClientService } from '../../services/client.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-post-commande',
  templateUrl: './post-commande.component.html',
  styleUrls: ['./post-commande.component.css']
})
export class PostCommandeComponent {
  commandeForm !: FormGroup;

  constructor(
    private clientService: ClientService,
    private fb : FormBuilder,
    private snacks : MatSnackBar,
    private router : Router,
    private matdialog : MatDialog
  ) {}



  ngOnInit(){
    this.commandeForm = this.fb.group({
      adresse : [null,Validators.required],
      paiment: [null, Validators.required]
    })
  }


  passerCommande(){
    this.clientService.passerCommande(this.commandeForm.value).subscribe(res =>{
      if(res.id != null){
        this.matdialog.closeAll();
        this.snacks.open('Commande passée avec succès','Fermer',{ duration : 5000});
        this.router.navigateByUrl("client/tout-commandes");
      }else{
        this.snacks.open('quelque chose s"est mal passé','Fermer',{ duration : 5000});
      }
    })
  }


  closeForm(){
    this.matdialog.closeAll();
  }
}
