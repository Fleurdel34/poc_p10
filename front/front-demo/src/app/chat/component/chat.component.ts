import { Component, DestroyRef, inject, OnInit} from '@angular/core';
import { Message } from '../model/message.model';
import { FormGroup, Validators, FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ChatService } from '../../services/chat.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [ReactiveFormsModule, AsyncPipe],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.scss',
})
export class ChatComponent implements OnInit {

private formBuilder = inject(FormBuilder);
private destroyRef = inject(DestroyRef);
private chatService = inject(ChatService);

public messages$ = new BehaviorSubject<Message[]>([]);
messageForm !: FormGroup;
messages: any;


  ngOnInit() :void{
    this.messageForm = this.formBuilder.group({
      content: [null, [Validators.required]]
    });

    this.chatService.getAllMessages()
    .pipe(takeUntilDestroyed(this.destroyRef))
    .subscribe(messages => this.messages$.next(messages));

    this.chatService.streamMessages()
    .pipe(takeUntilDestroyed(this.destroyRef))
    .subscribe(message => {
      const currentMessages = this.messages$.value;
      if (!currentMessages.find(m => m.id === message.id)) {
          this.messages$.next([...currentMessages, message]);
        }
    });
  }

  onSubmit() : void {
    const formValue = this.messageForm.value;
    if (this.messageForm.valid) {
      this.chatService.createMessage(formValue)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe();
      this.messageForm.reset();
    }
  }
}