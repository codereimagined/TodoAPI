--changeset todos
create table todos (
  id bigint primary key,
  item text not null,
  completed_at timestamptz
);

--changeset todos:initial-data context:dev
insert into todos (id, item) values (123, 'Buy groceries');
