import { NgIf } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import {
  ReactiveFormsModule,
  FormGroup,
  FormBuilder,
  Validators,
} from '@angular/forms';
import { ItInputComponent, ItSelectComponent } from 'design-angular-kit';
import { MapInputComponent } from '../../map-input/map-input.component';

@Component({
  selector: 'app-land',
  standalone: true,
  imports: [
    MapInputComponent,
    ItInputComponent,
    ItSelectComponent,
    ReactiveFormsModule,
    NgIf,
  ],
  templateUrl: './land.component.html',
  styleUrl: './land.component.scss',
})
export class LandComponent implements OnInit {
  @Input()
  formContainer?: FormGroup;

  years: any[] = [...Array(10).keys()]
    .map((i) => ({
      value: '' + (new Date().getFullYear() - i),
      text: '' + (new Date().getFullYear() - i),
    }))
    .concat([{ value: '', text: 'Seleziona anno' }]);

  constructor(private _fb: FormBuilder) {}

  ngOnInit(): void {
    if (this.formContainer) {
      this.formContainer.addControl(
        'parameters',
        this._fb.group({
          startDate: ['', Validators.required],
          endDate: ['', Validators.required],
          outputName: ['', Validators.required],
          geometry: ['', Validators.required],
        }),
      );
    }
  }

  updateStartDate(event: any) {
    let v = event.target.value;
    this.formContainer?.patchValue({ parameters: { startDate: v } });
    this.formContainer?.updateValueAndValidity();
  }

  updateEndDate(event: any) {
    let v = event.target.value;
    this.formContainer?.patchValue({ parameters: { endDate: v } });
    this.formContainer?.updateValueAndValidity();
  }

  getPolyline(event: any): void {
    if (event.type === 'draw:created') {
      let polyline = event.layer.getLatLngs()[0];
      console.log('Polyline created:', polyline);
      this.formContainer?.patchValue({
        parameters: { geometry: this.toGeometryString(polyline) },
      });
      this.formContainer?.updateValueAndValidity();
    } else if (event.type === 'draw:deleted') {
      this.formContainer?.patchValue({ parameters: { geometry: null } });
      this.formContainer?.updateValueAndValidity();
    }
  }
  toGeometryString(polyline: any) {
    polyline.push(polyline[0]); // Close the polygon by adding the first point at the end
    return (
      'POLYGON((' +
      polyline.map((point: any) => `${point.lng} ${point.lat}`).join(', ') +
      '))'
    );
  }
}
