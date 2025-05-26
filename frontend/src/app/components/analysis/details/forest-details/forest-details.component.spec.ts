import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForestDetailsComponent } from './forest-details.component';

describe('ForestDetailsComponent', () => {
  let component: ForestDetailsComponent;
  let fixture: ComponentFixture<ForestDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ForestDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ForestDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
