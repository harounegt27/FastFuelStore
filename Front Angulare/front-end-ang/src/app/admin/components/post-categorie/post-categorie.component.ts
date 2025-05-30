import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-post-categorie',
  templateUrl: './post-categorie.component.html',
  styleUrls: ['./post-categorie.component.css']
})
export class PostCategorieComponent {
  categorieForm !: FormGroup

  constructor(
    private fb : FormBuilder,
    private router : Router,
    private snacks : MatSnackBar,
    private adminService : AdminService
  ){}

  ngOnInit():void{
    this.categorieForm = this.fb.group({
      name : [null , [Validators.required]],
      description : [null , [Validators.required]],
    })
  }

  addCategori():void{
    if(this.categorieForm.valid){
      this.adminService.addCategorie(this.categorieForm.value).subscribe((res)=>{
        if(res.id =! null){
          this.snacks.open('Catégorie ajouter avec succès !','Fermer',{duration : 5000});
          this.router.navigateByUrl('/');
        }else{
          this.snacks.open(res.message,'Fermer',{duration:5000,panelClass:'error-snackbar'});
        }
      })
    }else{
      this.categorieForm.markAllAsTouched();
    }
  }
}
