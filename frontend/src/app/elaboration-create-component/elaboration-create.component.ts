import { AfterViewInit, Component } from '@angular/core';
import { Elaboration, ElaborationService } from '../elaboration.service';
import { ItButtonDirective, ItInputComponent, ItNotificationService, ItSelectComponent } from 'design-angular-kit';
import { FormBuilder, FormGroup, FormsModule, Validators, ReactiveFormsModule } from '@angular/forms';
import * as L from 'leaflet';
import { MapInputComponent } from '../components/map-input/map-input.component';
import { ForestComponent } from '../components/analysis/forest/forest.component';
import { Router, RouterLink } from '@angular/router';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-elaboration-create',
  standalone: true,
  imports: [NgIf, ItInputComponent, ItSelectComponent, ReactiveFormsModule, MapInputComponent, ForestComponent, ItButtonDirective, RouterLink],
  templateUrl: './elaboration-create.component.html',
  styleUrl: './elaboration-create.component.scss'
})
export class ElaborationCreateComponent {
  
  typeOptions = [
    {value: '', text: 'Seleziona tipologia di analisi'},
    {value: 'forest', text: 'Analisi di deforestazione'},
    {value: 'flood', text: 'Analisi di alluvione'},
    {value: 'land', text: 'Analisi geologica'},
  ]

  eForm: FormGroup  = this._fb.group({
      name: ['', Validators.required],
      localName: ['', Validators.required]
    });

  constructor(private _fb: FormBuilder, private router: Router, private elaborationService: ElaborationService, private readonly notificationService: ItNotificationService) {}

  protected save(): void {
    this.eForm.markAllAsTouched();
    if (this.eForm.invalid) {
      return;
    }
    console.log(this.eForm.value);
    this.elaborationService.createElaboration(this.eForm.value as Elaboration).subscribe((res) => {
      this.notificationService.success('Successo', 'Elaborazione creata con successo');
      this.router.navigate(['/']);
    });
  }
}
