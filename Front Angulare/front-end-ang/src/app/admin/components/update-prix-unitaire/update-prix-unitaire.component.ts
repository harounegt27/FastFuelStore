import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-update-prix-unitaire',
  templateUrl: './update-prix-unitaire.component.html',
  styleUrls: ['./update-prix-unitaire.component.css']
})
export class UpdatePrixUnitaireComponent {

  prixId = this.activatedRoute.snapshot.params['prixId'];

  prixunitaireForm !: FormGroup
  listOfVoix: any[]
  listOfProduit: any[]


  constructor(
    private fb : FormBuilder,
    private router : Router,
    private snacks : MatSnackBar,
    private adminService : AdminService,
    private activatedRoute : ActivatedRoute
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
    this.getPrixById();
  }

  allProduit(){
    this.adminService.allProduitPU().subscribe(res =>{
      this.listOfProduit = res;
    })
  }

  allVoix(){
    this.adminService.allVoix().subscribe(res =>{
      this.listOfVoix = res;
    })
  }

  getPrixById(){
    this.adminService.getPrixById(this.prixId).subscribe(rep =>{
      this.prixunitaireForm.patchValue(rep);
    })
  }


  updatePrice():void {
    if(this.prixunitaireForm.valid){
      const formData = new FormData();
      formData.append('price', this.prixunitaireForm.get('price').value);
      formData.append('frais1', this.prixunitaireForm.get('frais1').value);
      formData.append('frais2', this.prixunitaireForm.get('frais2').value);
      const today = new Date();
      const formattedToday = today.toISOString().slice(0, 10);

      formData.append('structure_prix', formattedToday);
      formData.append('voixId', this.prixunitaireForm.get('voixId').value);
      formData.append('produitId', this.prixunitaireForm.get('produitId').value);

      this.adminService.updatePrix(this.prixId,formData).subscribe((res) => {
        if (res != null){
          this.snacks.open('Prix unitaire modifier avec succès !','Fermer',{duration : 5000});
          this.router.navigateByUrl('/admin/tout-prix');
        } else{
          this.snacks.open(res.message, 'ERROR' ,{duration : 5000});
        }
      })
    }else{
      this.prixunitaireForm.markAllAsTouched();
    }

  }

}
