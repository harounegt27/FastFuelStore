import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-post-prix-unitaire',
  templateUrl: './post-prix-unitaire.component.html',
  styleUrls: ['./post-prix-unitaire.component.css']
})
export class PostPrixUnitaireComponent {

  prixunitaireForm !: FormGroup
  listOfVoix: any[]
  listOfProduit: any[]


  constructor(
    private fb : FormBuilder,
    private router : Router,
    private snacks : MatSnackBar,
    private adminService : AdminService
  ){}

  ngOnInit():void{
    this.prixunitaireForm = this.fb.group({
      price : [null , [Validators.required]],
      frais1 : [null , [Validators.required]],
      frais2 : [null , [Validators.required]],
      voixId : [null , [Validators.required]],
      structure_prix : [null , [Validators.required]],
      produitId : [null , [Validators.required]],
    })
    this.allVoix();
    this.allProduit();
  }

  allProduit(){
    this.adminService.allProduit().subscribe(res =>{
      this.listOfProduit = res;
    })
  }

  allVoix(){
    this.adminService.allVoix().subscribe(res =>{
      this.listOfVoix = res;
    })
  }


  addPrice():void {
    if(this.prixunitaireForm.valid){
      const formData = new FormData();
      formData.append('price', this.prixunitaireForm.get('price').value);
      formData.append('frais1', this.prixunitaireForm.get('frais1').value);
      formData.append('frais2', this.prixunitaireForm.get('frais2').value);
      formData.append('structure_prix', this.prixunitaireForm.get('structure_prix').value);
      formData.append('voixId', this.prixunitaireForm.get('voixId').value);
      formData.append('produitId', this.prixunitaireForm.get('produitId').value);

      this.adminService.addPrixUnitaire(formData).subscribe((res) => {
        if (res != null){
          this.snacks.open('Prix unitaire ajouter avec succès !','Fermer',{duration : 5000});
          this.router.navigateByUrl('/admin/ajouter-prixunitaire');
        } else{
          this.snacks.open(res.message, 'ERROR' ,{duration : 5000});
        }
      })
    }else{
      this.prixunitaireForm.markAllAsTouched();
    }

  }


}
