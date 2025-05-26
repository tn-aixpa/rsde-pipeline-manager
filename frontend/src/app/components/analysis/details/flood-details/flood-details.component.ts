import { Component, Input } from '@angular/core';
import { Elaboration } from '../../../../elaboration.service';

@Component({
  selector: 'app-flood-details',
  standalone: true,
  imports: [],
  templateUrl: './flood-details.component.html',
  styleUrl: './flood-details.component.scss'
})
export class FloodDetailsComponent {
  @Input()
  elaboration?: Elaboration; // Define the type based on your Elaboration model, e.g., Elaboration

}
