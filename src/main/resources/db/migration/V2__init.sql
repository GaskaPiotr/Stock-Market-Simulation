CREATE TABLE event_publication (
    id UUID PRIMARY KEY,
    listener_id TEXT NOT NULL,
    event_type TEXT NOT NULL,
    serialized_event TEXT NOT NULL,
    publication_date TIMESTAMP WITH TIME ZONE NOT NULL,
    completion_date TIMESTAMP WITH TIME ZONE,
    status TEXT,
    completion_attempts INT,
    last_resubmission_date TIMESTAMP WITH TIME ZONE
);