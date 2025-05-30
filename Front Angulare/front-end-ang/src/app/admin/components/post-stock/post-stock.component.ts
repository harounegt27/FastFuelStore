import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-post-stock',
  templateUrl: './post-stock.component.html',
  styleUrls: ['./post-stock.component.css']
})
export class PostStockComponent {
  stockForm !: FormGroup
  listOfProduit: any[]
  conversionFactor: number;

  constructor(
    private fb : FormBuilder,
    private router : Router,
    private snacks : MatSnackBar,
    private adminService : AdminService
  )
  {}

  ngOnInit():void{
    this.stockForm = this.fb.group({
      conversionFactor: [1, [Validators.required]],
      en_tm : [null , [Validators.required]],
      en_m3 : [null , [Validators.required]],
      indique_en_tm : [null , [Validators.required]],
      indique_en_m3 : [null , [Validators.required]],
      produitId : [null , [Validators.required]],
    })
    this.allProduit();

  }

  allProduit(){
    this.adminService.allProduit().subscribe(res =>{
      this.listOfProduit = res;
    })
  }

  // Méthode appelée lorsqu'une valeur est saisie dans le champ de mètres cubes
  onCubicMeterChange(event: any): void {
    try {
        const value = event.target.value;
        if (typeof value !== 'undefined') {
            const metricTons = parseFloat(value) / this.stockForm.get('conversionFactor').value; // Convertir en tonnes métriques
            this.stockForm.patchValue({ en_m3: metricTons });
        }
    } catch (error) {
        console.error("Erreur dans onCubicMeterChange:", error);
    }
}

onMetricTonChange(event: any): void {
    try {
        const value = event.target.value;
        if (typeof value !== 'undefined') {
            const cubicMeters = parseFloat(value) * this.stockForm.get('conversionFactor').value; // Convertir en mètres cubes
            this.stockForm.patchValue({ en_tm: cubicMeters });
        }
    } catch (error) {
        console.error("Erreur dans onMetricTonChange:", error);
    }
}


// Méthode appelée lorsqu'une valeur est saisie dans le champ de tonnes métriques


  addStock():void {
    if(this.stockForm.valid){
      const formData = new FormData();
      formData.append('en_tm', this.stockForm.get('en_tm').value);
      formData.append('en_m3', this.stockForm.get('en_m3').value);
      formData.append('indique_en_tm', this.stockForm.get('indique_en_tm').value);
      formData.append('indique_en_m3', this.stockForm.get('indique_en_m3').value);
      formData.append('produitId', this.stockForm.get('produitId').value);

      this.adminService.addStock(formData).subscribe((res) => {
        if (res != null){
          this.snacks.open('Stock ajouter avec succès !','Fermer',{duration : 5000});
          this.router.navigateByUrl('/admin');
        } else{
          this.snacks.open(res.message, 'ERROR' ,{duration : 5000});
        }
      })
    }else{
      this.stockForm.markAllAsTouched();
    }

  }
}
