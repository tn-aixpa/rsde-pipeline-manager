import { Component, Input } from '@angular/core';
import {
  Elaboration,
  ElaborationService,
} from '../../../../elaboration.service';
import { MapVisComponent } from '../../../map-vis/map-vis.component';
import { NgForOf, NgIf } from '@angular/common';

@Component({
  selector: 'app-land-details',
  standalone: true,
  imports: [MapVisComponent, NgIf, NgForOf],
  templateUrl: './land-details.component.html',
  styleUrl: './land-details.component.scss',
})
export class LandDetailsComponent {
  @Input()
  elaboration?: Elaboration; // Define the type based on your Elaboration model, e.g., Elaboration

  links?: any[];

  constructor(private elaborationService: ElaborationService) {}
  ngOnInit(): void {
    if (this.elaboration?.status == 'COMPLETED') {
      this.elaborationService
        .generatePresignedUrl(
          this.elaboration.id!,
          this.elaboration.parameters!.outputName!,
        )
        .subscribe((value) => {
          this.links = Object.keys(value).map((k) => ({
            name: k,
            value: value[k],
          }));
        });
    }
  }
}
