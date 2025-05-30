import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirmation-dialog',
  template: `
    <h2 mat-dialog-title>Confirmation</h2>
    <div mat-dialog-content>
      <p>{{ message }}</p>
    </div>
    <div mat-dialog-actions>
      <button mat-button (click)="closeDialog(true)">Confirmer</button>
      <button mat-button (click)="closeDialog(false)">Annuler</button>
    </div>
  `,
})
export class ConfirmationDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<ConfirmationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public message: string
  ) {}

  closeDialog(confirmed: boolean): void {
    this.dialogRef.close(confirmed);
  }
}
