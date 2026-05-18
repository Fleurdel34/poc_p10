export interface Message {
  id: number;
  content: string;
  response: string | null;
  user_id: number | null;
}
