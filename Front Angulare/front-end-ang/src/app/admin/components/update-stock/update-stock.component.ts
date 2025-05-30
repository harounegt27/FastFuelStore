import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-update-stock',
  templateUrl: './update-stock.component.html',
  styleUrls: ['./update-stock.component.css']
})
export class UpdateStockComponent {

  stockId = this.activatedRoute.snapshot.params['stockId'];

  stockForm !: FormGroup
  listOfProduit: any[]
  conversionFactor: number;

  constructor(
    private fb : FormBuilder,
    private router : Router,
    private snacks : MatSnackBar,
    private adminService : AdminService,
    private activatedRoute : ActivatedRoute
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
    this.getStockById();
  }

  allProduit(){
    this.adminService.allProduit().subscribe(res =>{
      this.listOfProduit = res;
    })
  }

  getStockById(){
    this.adminService.getStockById(this.stockId).subscribe(rep=>{
      this.stockForm.patchValue(rep);
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


  updateStock():void {
    if(this.stockForm.valid){
      const formData = new FormData();
      formData.append('en_tm', this.stockForm.get('en_tm').value);
      formData.append('en_m3', this.stockForm.get('en_m3').value);
      formData.append('indique_en_tm', this.stockForm.get('indique_en_tm').value);
      formData.append('indique_en_m3', this.stockForm.get('indique_en_m3').value);
      formData.append('produitId', this.stockForm.get('produitId').value);

      this.adminService.updateStock(this.stockId,formData).subscribe((res) => {
        if (res != null){
          this.snacks.open('Stock modifier avec succès !','Fermer',{duration : 5000});
          this.router.navigateByUrl('/admin/tout-stocks');
        } else{
          this.snacks.open(res.message, 'ERROR' ,{duration : 5000});
        }
      })
    }else{
      this.stockForm.markAllAsTouched();
    }

  }
}
