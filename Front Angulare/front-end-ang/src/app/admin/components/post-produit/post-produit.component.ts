import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';
import { every } from 'rxjs';

@Component({
  selector: 'app-post-produit',
  templateUrl: './post-produit.component.html',
  styleUrls: ['./post-produit.component.css']
})
export class PostProduitComponent {
  produitForm !: FormGroup
  listOfCategorie: any[];

  selectedFile: File | null;
  imagePreview: string | ArrayBuffer |null;

  constructor(
    private fb : FormBuilder,
    private router : Router,
    private snacks : MatSnackBar,
    private adminService : AdminService
  ){}


  onFileSelected(event : any){
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

  previewImage(){
    const reader = new FileReader;
    reader.onload = () => {
      this.imagePreview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }

  ngOnInit():void{
    this.produitForm = this.fb.group({
      description : [null, Validators.required],
      name : [null, Validators.required],
      densite : [null, Validators.required],
      prixUnitaireId : [null],
      categorieId : [null, Validators.required]
    })
    this.allCategories();

  }

  allCategories(){
    this.adminService.allCategorie().subscribe(res =>{
      this.listOfCategorie = res;
    })
  }



  addProduit(): void{
    if(this.produitForm.valid){
      const formData = new FormData();
      formData.append('img', this.selectedFile);
      formData.append('categorieId', this.produitForm.get('categorieId').value);
      formData.append('description', this.produitForm.get('description').value);
      formData.append('name', this.produitForm.get('name').value);
      formData.append('densite', this.produitForm.get('densite').value);

      this.adminService.addPoduit(formData).subscribe((res) => {
        if (res != null){
          this.snacks.open('Produit ajouter avec succès !','Fermer',{duration : 5000});
          this.router.navigateByUrl('/admin/dashboard');
        } else{
          this.snacks.open(res.message, 'ERROR' ,{duration : 5000});
        }
      })
    }else{
      for(const i in this.produitForm.controls) {
        this.produitForm.controls[i].markAsDirty;
        this.produitForm.controls[i].updateValueAndValidity;
      }

    }
  }



}
