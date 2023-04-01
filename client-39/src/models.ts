export interface Character {
    id: string;
    name: string;
    description?: string;
    imageUrl?: string;
}

export interface UserComment{
    id: string;
    comment: string;
    timestamp: string;
}