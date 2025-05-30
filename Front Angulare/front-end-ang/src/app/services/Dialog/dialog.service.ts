import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from 'src/app/confirmation-dialog-component/confirmation-dialog-component.component';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class DialogService {
  constructor(private dialog: MatDialog) { }

  confirm(message: string): Observable<boolean> {
    const dialogRef: MatDialogRef<ConfirmationDialogComponent> = this.dialog.open(ConfirmationDialogComponent, {
      width: '400px',
      data: message
    });

    return dialogRef.afterClosed();
  }

}
