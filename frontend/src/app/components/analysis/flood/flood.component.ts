import { JsonPipe, NgIf } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ItInputComponent, ItSelectComponent } from 'design-angular-kit';
import { MapInputComponent } from '../../map-input/map-input.component';

@Component({
  selector: 'app-flood',
  standalone: true,
  imports: [MapInputComponent, ItInputComponent, ItSelectComponent, ReactiveFormsModule, NgIf, JsonPipe ],
  templateUrl: './flood.component.html',
  styleUrl: './flood.component.scss'
})
export class FloodComponent implements OnInit {

  // floodDate, outputName
  // fixed: input1, input2, input3, input4, input5, input6

  @Input()
  formContainer?: FormGroup;

  years: any[] = [...Array(10).keys()].map(i => ({value: "" + (new Date().getFullYear() - i), text: "" + (new Date().getFullYear() - i)})).concat([
    {value: '', text: 'Seleziona anno'}
  ]);


  constructor(private _fb: FormBuilder) {}

  ngOnInit(): void {
    if (this.formContainer) {
      this.formContainer.addControl('parameters', this._fb.group({
        floodDate: ['', Validators.required],
        outputName: ['', Validators.required],
        geometry: ['', Validators.required],
      }));
    }
  }

  updateDate(event: any) {
    let v = event.target.value;
    let date = new Date(Date.parse(v));
    // dd-mm-yyyy
    v = ((date.getDate() > 9) ? date.getDate() : ('0' + date.getDate())) + '-' + ((date.getMonth() > 8) ? (date.getMonth() + 1) : ('0' + (date.getMonth() + 1))) + '-' + date.getFullYear();
    this.formContainer?.patchValue({"parameters": {"floodDate": v}});
    this.formContainer?.updateValueAndValidity();
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
