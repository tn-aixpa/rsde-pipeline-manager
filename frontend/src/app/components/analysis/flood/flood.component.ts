import { JsonPipe, NgIf } from '@angular/common';
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
  selector: 'app-flood',
  standalone: true,
  imports: [
    MapInputComponent,
    ItInputComponent,
    ItSelectComponent,
    ReactiveFormsModule,
    NgIf,
    JsonPipe,
  ],
  templateUrl: './flood.component.html',
  styleUrl: './flood.component.scss',
})
export class FloodComponent implements OnInit {
  // floodDate, outputName
  // fixed: input1, input2, input3, input4, input5, input6

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
          floodDate: ['', Validators.required],
          s1_preFloodDate: ['', Validators.required],
          s1_postFloodDate: ['', Validators.required],
          s2_preFloodDate: ['', Validators.required],
          s2_postFloodDate: ['', Validators.required],
          outputName: ['', Validators.required],
          geometry: ['', Validators.required],
        }),
      );
    }
  }

  updateDate(event: any) {
    let v = event.target.value;
    let date = new Date(Date.parse(v));
    // yyyy-mm-dd
    // s1: +- 7 days
    // s2: +- 20 days

    date.setDate(date.getDate() - 7);
    let s1_preFloodDate = date.toISOString().split('T')[0];
    date.setDate(date.getDate() + 14);
    let s1_postFloodDate = date.toISOString().split('T')[0];
    date.setDate(date.getDate() - 27);
    let s2_preFloodDate = date.toISOString().split('T')[0];
    date.setDate(date.getDate() + 40);
    let s2_postFloodDate = date.toISOString().split('T')[0];

    this.formContainer?.patchValue({ parameters: { floodDate: v, s1_preFloodDate, s1_postFloodDate, s2_preFloodDate, s2_postFloodDate } });
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
