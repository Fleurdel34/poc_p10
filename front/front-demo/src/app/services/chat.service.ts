import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable, map } from 'rxjs';
import { FormGroup } from '@angular/forms';
import { Message } from '../chat/model/message.model';


@Injectable({
    providedIn: 'root'
})
export class ChatService {
    private pathApi = environment.apiUrl;

    private http = inject(HttpClient);

    public createMessage(formValue:FormGroup): Observable<void> {
        return this.http.post<void>(`${this.pathApi}/message`, formValue);
    }

    public getAllMessages(): Observable<Message[]> {
        return this.http.get<{messages: Message[]}>(`${this.pathApi}/message`).pipe(
            map(response => response.messages)
        );
    }

    public streamMessages(): Observable<Message> {
        return new Observable<Message>(observer => {
        const eventSource = new EventSource(`${this.pathApi}/message/stream`);

        eventSource.onmessage = event => observer.next(JSON.parse(event.data));
        eventSource.onerror = () => eventSource.close();

        return () => eventSource.close();
    });
}

}