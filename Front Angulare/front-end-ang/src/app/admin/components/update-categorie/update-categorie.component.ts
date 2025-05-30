import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-update-categorie',
  templateUrl: './update-categorie.component.html',
  styleUrls: ['./update-categorie.component.css']
})
export class UpdateCategorieComponent {
  categorieForm!: FormGroup;
  categorieId!: number;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private snacks: MatSnackBar,
    private adminService: AdminService
  ) {}

  ngOnInit(): void {
    // Récupérer l'ID de la catégorie depuis les paramètres d'URL
    this.route.params.subscribe(params => {
      this.categorieId = +params['id']; // Convertir en nombre
    });

    // Initialiser le formulaire et charger les détails de la catégorie à mettre à jour
    this.categorieForm = this.fb.group({
      name: [null, [Validators.required]],
      description: [null, [Validators.required]],
    });

    this.loadCategorieDetails();
  }

  loadCategorieDetails(): void {
    // Appeler le service pour récupérer les détails de la catégorie en fonction de son ID
    this.adminService.getCategorieById(this.categorieId).subscribe((categorie) => {
      // Remplir le formulaire avec les détails de la catégorie récupérée
      this.categorieForm.patchValue({
        name: categorie.name,
        description: categorie.description,
      });
    });
  }

  updateCategorie(): void {
    if (this.categorieForm.valid) {
      this.adminService.updateCategorie(this.categorieId, this.categorieForm.value).subscribe((res) => {
        if (res.id != null) {
          this.snacks.open('Catégorie mise à jour avec succès !', 'Fermer', { duration: 5000 });
          this.router.navigateByUrl('/admin/tout-categorie'); // Rediriger vers la liste des catégories après la mise à jour
        } else {
          this.snacks.open(res.message, 'Fermer', { duration: 5000, panelClass: 'error-snackbar' });
        }
      });
    } else {
      this.categorieForm.markAllAsTouched();
    }
  }

}
