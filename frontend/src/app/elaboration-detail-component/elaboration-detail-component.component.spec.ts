import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElaborationDetailComponentComponent } from './elaboration-detail-component.component';

describe('ElaborationDetailComponentComponent', () => {
  let component: ElaborationDetailComponentComponent;
  let fixture: ComponentFixture<ElaborationDetailComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ElaborationDetailComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ElaborationDetailComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
