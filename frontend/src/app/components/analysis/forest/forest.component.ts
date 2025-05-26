import { Component, Input, OnInit } from '@angular/core';
import { MapInputComponent } from '../../map-input/map-input.component';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ItInputComponent, ItSelectComponent } from 'design-angular-kit';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-forest',
  standalone: true,
  imports: [MapInputComponent, ItInputComponent, ItSelectComponent, ReactiveFormsModule, NgIf ],
  templateUrl: './forest.component.html',
  styleUrl: './forest.component.scss'
})
export class ForestComponent implements OnInit {

  // startYear, endYear, geometry, outputName
  // fixed: input1, input2

  @Input()
  formContainer?: FormGroup;

  years: any[] = [...Array(10).keys()].map(i => ({value: "" + (new Date().getFullYear() - i), text: "" + (new Date().getFullYear() - i)})).concat([
    {value: '', text: 'Seleziona anno'}
  ]);


  constructor(private _fb: FormBuilder) {}

  ngOnInit(): void {
    if (this.formContainer) {
      this.formContainer.addControl('parameters', this._fb.group({
        startYear: ['', Validators.required],
        endYear: ['', Validators.required],
        outputName: ['', Validators.required],
        geometry: ['', Validators.required],
        input1: ['bosco'],
        inptu2: ['data_s2_deforestation']
      }));
    }
  }
  
  getPolyline(event: any): void {
     if (event.type === 'draw:created') {
      let polyline = event.layer.getLatLngs()[0];
      console.log('Polyline created:', polyline);
      this.formContainer?.patchValue({"parameters": {"geometry": this.toGeometryString(polyline)}});
      this.formContainer?.updateValueAndValidity();
    } else if (event.type === 'draw:deleted') {
      this.formContainer?.patchValue({"parameters": {"geometry": null}});
      this.formContainer?.updateValueAndValidity();
    } 
  }
  toGeometryString(polyline: any) {
    return 'POLYGON((' + polyline.map((point: any) => `${point.lng} ${point.lat}`).join(', ') + '))';
  }

}
