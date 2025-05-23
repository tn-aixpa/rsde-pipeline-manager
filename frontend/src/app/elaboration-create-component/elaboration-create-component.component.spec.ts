import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElaborationCreateComponentComponent } from './elaboration-create-component.component';

describe('ElaborationCreateComponentComponent', () => {
  let component: ElaborationCreateComponentComponent;
  let fixture: ComponentFixture<ElaborationCreateComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ElaborationCreateComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ElaborationCreateComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
