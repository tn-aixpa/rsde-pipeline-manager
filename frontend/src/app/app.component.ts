import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ItHeaderComponent, ItDropdownComponent, ItDropdownItemComponent, ItIconComponent, ItNotificationsComponent } from 'design-angular-kit';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ItHeaderComponent, ItDropdownComponent, ItDropdownItemComponent, ItIconComponent, ItNotificationsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend';
  light = false;
  sticky = false;
  search = false;
  login = 'none';
}
