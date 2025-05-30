import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserStorageService } from 'src/app/services/stockage/user-storage.service';
import { DialogService } from 'src/app/services/Dialog/dialog.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  updateForm!: FormGroup;
  userId: any = UserStorageService.getUserId();

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snacks: MatSnackBar,
    private clientService: ClientService,
    private userStorageService: UserStorageService, // Ensure this service is injected
    private dialog : DialogService
  ) {
  }

  ngOnInit(): void {
    this.updateForm = this.fb.group({
      name: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]]
    });
    this.getUserName();
  }

  getUserName(): void {
    this.clientService.getUserName(this.userId).subscribe(
      (response) => {
        this.updateForm.patchValue(response);
      },
      (error) => {
        console.error('Erreur lors de la récupération du nom du client : ', error);
      }
    );
  }

  onSubmit(): void {
    if (this.updateForm.valid) {
      const formData = new FormData();
      formData.append('name', this.updateForm.get('name')?.value);
      formData.append('email', this.updateForm.get('email')?.value);

      this.clientService.updateUser(formData).subscribe((res) => {
        if (res != null) {
          this.snacks.open('Informations modifiées avec succès !', 'Fermer', { duration: 5000 });
          this.router.navigateByUrl('/client/dashboards');
        } else {
          this.snacks.open(res.message, 'ERROR', { duration: 5000 });
        }
      });
    } else {
      for (const i in this.updateForm.controls) {
        if (this.updateForm.controls.hasOwnProperty(i)) {
          this.updateForm.controls[i].markAsDirty();
          this.updateForm.controls[i].updateValueAndValidity();
        }
      }
    }
  }

  deleteUser() {
    this.dialog.confirm('Êtes-vous sûr de vouloir supprimer compte FFS ?')
      .subscribe(confirmed => {
        if (confirmed) {
          this.clientService.deleteUser(this.userId).subscribe(
            () => {
              this.snacks.open('Client supprimé avec succès !', 'Fermer', { duration: 5000 });
              UserStorageService.signout;
            },
            (error) => {
              this.snacks.open('Une erreur s\'est produite lors de la suppression de la client.', 'Fermer', { duration: 5000, panelClass: 'error-snackbar' });
            }
          );
        } else {
          this.snacks.open('Suppression annulée.', 'Fermer', { duration: 5000 });
        }
      });
  }



}
