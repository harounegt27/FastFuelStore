import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-update-produit',
  templateUrl: './update-produit.component.html',
  styleUrls: ['./update-produit.component.css']
})
export class UpdateProduitComponent {

  produitId = this.acticatedRoute.snapshot.params['produitId'];

  produitForm !: FormGroup
  listOfCategorie: any[];
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer |null;
  existingImage : string | null = null;
  imgChanged = false;


  constructor(
    private fb : FormBuilder,
    private router : Router,
    private snacks : MatSnackBar,
    private adminService : AdminService,
    private acticatedRoute : ActivatedRoute
  ){}


  onFileSelected(event : any){
    this.selectedFile = event.target.files[0];
    this.previewImage();
    this.imgChanged = true;
    this.existingImage = null;
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
    this.getProduitById()

  }

  allCategories(){
    this.adminService.allCategorie().subscribe(res =>{
      this.listOfCategorie = res;
    })
  }

  getProduitById(){
    this.adminService.getPoduitById(this.produitId).subscribe(rep =>{
      this.produitForm.patchValue(rep);
      this.existingImage = 'data:image/jpeg;base64,' + rep.byteImg;
    })
  }

  updateProduit(): void{
    if(this.produitForm.valid){
      const formData = new FormData();
      if(this.imgChanged && this.selectedFile){
        formData.append('img', this.selectedFile);
      }
      formData.append('categorieId', this.produitForm.get('categorieId').value);
      formData.append('description', this.produitForm.get('description').value);
      formData.append('name', this.produitForm.get('name').value);
      formData.append('densite', this.produitForm.get('densite').value);

      this.adminService.updatePoduit(this.produitId,formData).subscribe((res) => {
        if (res != null){
          this.snacks.open('Produit modifier avec succès !','Fermer',{duration : 5000});
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
