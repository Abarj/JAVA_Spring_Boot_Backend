export class Metadata {
    
    sessionId: string;
    playerId: string;
    column: number;
    round: number;

    constructor(sessionId: string, playerId: string, column: number, round: number){
            this.sessionId = sessionId;
            this.playerId = playerId;
            this.column = column;
            this.round = round;
    }
}