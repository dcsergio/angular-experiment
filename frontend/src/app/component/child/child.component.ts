import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-child',
  templateUrl: './child.component.html',
  styleUrl: './child.component.css'
})
export class ChildComponent {

  htmlIronicChildMessages: string[] = [
    "Love the <strong>new chore</strong>.",
    "Broccoli is my <strong>favorite</strong>.",
    "Thrilled to be home <strong>early</strong>.",
    "Time flies (<strong>five minutes!</strong>).",
    "Needed that <strong>tenth reminder</strong>.",
    "Such a <strong>quiet</strong> car ride.",
    "Homework is <strong>too simple</strong>.",
    "Love <strong>6 AM</strong> weekends.",
    "No more candy? <strong>Totally fair</strong>.",
    "This sweater is <strong>so stylish</strong>.",
  ];

  index = 0;

  @Input() value: string = '10';
  @Output() updateParentEvent = new EventEmitter<string>();


  getMessage() {
    this.index = (this.index + 1) % 10;
    return (this.htmlIronicChildMessages[this.index]);
  }

}
