CREATE TABLE firm (
    a_id INTEGER PRIMARY KEY AUTOINCREMENT,
    _id  INTEGER,
    name TEXT
);

insert into firm(_id, name)
select firm_id, firm_name from firms;

insert into firm(a_id, _id, name)
values (0, 0, '');

ALTER TABLE firm RENAME TO sqlitestudio_temp_table;
CREATE TABLE firm (
    a_id INTEGER,
    _id  INTEGER PRIMARY KEY,
    name TEXT
);
INSERT INTO firm (
                     a_id,
                     _id,
                     name
                 )
                 SELECT a_id,
                        _id,
                        name
                   FROM sqlitestudio_temp_table;
DROP TABLE sqlitestudio_temp_table;



CREATE TABLE pharm_group (
    a_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    _id     INTEGER,
    content TEXT
);

insert into pharm_group(_id, content)
select distinct c.content_id, c._text from drugs d, content c where d.pharm_group = c.content_id;

insert into pharm_group(a_id, _id, content)
values (0, 0, '');

ALTER TABLE pharm_group RENAME TO sqlitestudio_temp_table;
CREATE TABLE pharm_group (
    a_id    INTEGER,
    _id     INTEGER PRIMARY KEY,
    content TEXT
);
INSERT INTO pharm_group (
                            a_id,
                            _id,
                            content
                        )
                        SELECT a_id,
                               _id,
                               content
                          FROM sqlitestudio_temp_table;
DROP TABLE sqlitestudio_temp_table;


CREATE TABLE pharm_action (
    a_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    _id     INTEGER,
    content TEXT
);

insert into pharm_action(_id, content)
select distinct c.content_id, c._text from drugs d, content c where d.pharm_action = c.content_id;

insert into pharm_action(a_id, _id, content)
values (0, 0, '');

ALTER TABLE pharm_action RENAME TO sqlitestudio_temp_table;
CREATE TABLE pharm_action (
    a_id    INTEGER,
    _id     INTEGER PRIMARY KEY,
    content TEXT
);
INSERT INTO pharm_action (
                            a_id,
                            _id,
                            content
                        )
                        SELECT a_id,
                               _id,
                               content
                          FROM sqlitestudio_temp_table;
DROP TABLE sqlitestudio_temp_table;


CREATE TABLE usage (
    a_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    _id     INTEGER,
    content TEXT
);

insert into usage(_id, content)
select distinct c.content_id, c._text from drugs d, content c where d.usage = c.content_id;

insert into usage(a_id, _id, content)
values (0, 0, '');

ALTER TABLE usage RENAME TO sqlitestudio_temp_table;
CREATE TABLE usage (
    a_id    INTEGER,
    _id     INTEGER PRIMARY KEY,
    content TEXT
);
INSERT INTO usage (
                            a_id,
                            _id,
                            content
                        )
                        SELECT a_id,
                               _id,
                               content
                          FROM sqlitestudio_temp_table;
DROP TABLE sqlitestudio_temp_table;


CREATE TABLE dosage (
    a_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    _id     INTEGER,
    content TEXT
);

insert into dosage(_id, content)
select distinct c.content_id, c._text from drugs d, content c where d.dosage = c.content_id;

insert into dosage(a_id, _id, content)
values (0, 0, '');

ALTER TABLE dosage RENAME TO sqlitestudio_temp_table;
CREATE TABLE dosage (
    a_id    INTEGER,
    _id     INTEGER PRIMARY KEY,
    content TEXT
);
INSERT INTO dosage (
                            a_id,
                            _id,
                            content
                        )
                        SELECT a_id,
                               _id,
                               content
                          FROM sqlitestudio_temp_table;
DROP TABLE sqlitestudio_temp_table;


CREATE TABLE side_effect (
    a_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    _id     INTEGER,
    content TEXT
);

insert into side_effect(_id, content)
select distinct c.content_id, c._text from drugs d, content c where d.side_effect = c.content_id;

insert into side_effect(a_id, _id, content)
values (0, 0, '');

ALTER TABLE side_effect RENAME TO sqlitestudio_temp_table;
CREATE TABLE side_effect (
    a_id    INTEGER,
    _id     INTEGER PRIMARY KEY,
    content TEXT
);
INSERT INTO side_effect (
                            a_id,
                            _id,
                            content
                        )
                        SELECT a_id,
                               _id,
                               content
                          FROM sqlitestudio_temp_table;
DROP TABLE sqlitestudio_temp_table;


CREATE TABLE contras (
    a_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    _id     INTEGER,
    content TEXT
);

insert into contras(_id, content)
select distinct c.content_id, c._text from drugs d, content c where d.contras = c.content_id;

insert into contras(a_id, _id, content)
values (0, 0, '');

ALTER TABLE contras RENAME TO sqlitestudio_temp_table;
CREATE TABLE contras (
    a_id    INTEGER,
    _id     INTEGER PRIMARY KEY,
    content TEXT
);
INSERT INTO contras (
                            a_id,
                            _id,
                            content
                        )
                        SELECT a_id,
                               _id,
                               content
                          FROM sqlitestudio_temp_table;
DROP TABLE sqlitestudio_temp_table;


CREATE TABLE special (
    a_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    _id     INTEGER,
    content TEXT
);

insert into special(_id, content)
select distinct c.content_id, c._text from drugs d, content c where d.special = c.content_id;

insert into special(a_id, _id, content)
values (0, 0, '');

ALTER TABLE special RENAME TO sqlitestudio_temp_table;
CREATE TABLE special (
    a_id    INTEGER,
    _id     INTEGER PRIMARY KEY,
    content TEXT
);
INSERT INTO special (
                            a_id,
                            _id,
                            content
                        )
                        SELECT a_id,
                               _id,
                               content
                          FROM sqlitestudio_temp_table;
DROP TABLE sqlitestudio_temp_table;


CREATE TABLE overdose (
    a_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    _id     INTEGER,
    content TEXT
);

insert into overdose(_id, content)
select distinct c.content_id, c._text from drugs d, content c where d.overdose = c.content_id;

insert into overdose(a_id, _id, content)
values (0, 0, '');

ALTER TABLE overdose RENAME TO sqlitestudio_temp_table;
CREATE TABLE overdose (
    a_id    INTEGER,
    _id     INTEGER PRIMARY KEY,
    content TEXT
);
INSERT INTO overdose (
                            a_id,
                            _id,
                            content
                        )
                        SELECT a_id,
                               _id,
                               content
                          FROM sqlitestudio_temp_table;
DROP TABLE sqlitestudio_temp_table;


CREATE TABLE interaction (
    a_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    _id     INTEGER,
    content TEXT
);

insert into interaction(_id, content)
select distinct c.content_id, c._text from drugs d, content c where d.interaction = c.content_id;

insert into interaction(a_id, _id, content)
values (0, 0, '');

ALTER TABLE interaction RENAME TO sqlitestudio_temp_table;
CREATE TABLE interaction (
    a_id    INTEGER,
    _id     INTEGER PRIMARY KEY,
    content TEXT
);
INSERT INTO interaction (
                            a_id,
                            _id,
                            content
                        )
                        SELECT a_id,
                               _id,
                               content
                          FROM sqlitestudio_temp_table;
DROP TABLE sqlitestudio_temp_table;


CREATE TABLE storage (
    a_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    _id     INTEGER,
    content TEXT
);

insert into storage(_id, content)
select distinct c.content_id, c._text from drugs d, content c where d.storage = c.content_id;

insert into storage(a_id, _id, content)
values (0, 0, '');

ALTER TABLE storage RENAME TO sqlitestudio_temp_table;
CREATE TABLE storage (
    a_id    INTEGER,
    _id     INTEGER PRIMARY KEY,
    content TEXT
);
INSERT INTO storage (
                            a_id,
                            _id,
                            content
                        )
                        SELECT a_id,
                               _id,
                               content
                          FROM sqlitestudio_temp_table;
DROP TABLE sqlitestudio_temp_table;


CREATE TABLE composition (
    a_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    _id     INTEGER,
    content TEXT
);

insert into composition(_id, content)
select distinct c.content_id, c._text from drugs d, content c where d.composition = c.content_id;

insert into composition(a_id, _id, content)
values (0, 0, '');

ALTER TABLE composition RENAME TO sqlitestudio_temp_table;
CREATE TABLE composition (
    a_id    INTEGER,
    _id     INTEGER PRIMARY KEY,
    content TEXT
);
INSERT INTO composition (
                            a_id,
                            _id,
                            content
                        )
                        SELECT a_id,
                               _id,
                               content
                          FROM sqlitestudio_temp_table;
DROP TABLE sqlitestudio_temp_table;


CREATE TABLE drug (
    _id             INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT    NOT NULL,
    firm_id         INTEGER,
    pharm_group_id  INTEGER REFERENCES pharm_group (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    pharm_action_id INTEGER REFERENCES pharm_action (_id) ON DELETE CASCADE
                                                          ON UPDATE CASCADE,
    usage_id        INTEGER REFERENCES usage (_id) ON DELETE CASCADE
                                                   ON UPDATE CASCADE,
    dosage_id       INTEGER REFERENCES dosage (_id) ON DELETE CASCADE
                                                    ON UPDATE CASCADE,
    side_effect_id  INTEGER REFERENCES side_effect (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    contras_id      INTEGER REFERENCES contras (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    special_id      INTEGER REFERENCES special (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    overdose_id     INTEGER REFERENCES overdose (_id) ON DELETE CASCADE
                                                      ON UPDATE CASCADE,
    interaction_id  INTEGER REFERENCES interaction (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    storage_id      INTEGER REFERENCES storage (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    composition_id  INTEGER REFERENCES composition (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE
);


insert into drug(name, firm_id, pharm_group_id, pharm_action_id, usage_id, dosage_id, side_effect_id, contras_id, special_id, overdose_id, interaction_id, storage_id, composition_id)
select name, firm_id, pharm_group, pharm_action, usage, dosage, side_effect, contras, special, overdose, interaction, storage, composition from drugs; 


update firm set _id = a_id;
update pharm_group set _id = a_id;
update pharm_action set _id = a_id;
update usage set _id = a_id;
update dosage set _id = a_id;
update side_effect set _id = a_id;
update contras set _id = a_id;
update special set _id = a_id;
update overdose set _id = a_id;
update interaction set _id = a_id;
update storage set _id = a_id;
update composition set _id = a_id;


ALTER TABLE firm RENAME TO sqlitestudio_temp_table;
CREATE TABLE firm (
    _id  INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT
);
INSERT INTO firm (
                     _id,
                     name
                 )
                 SELECT _id,
                        name
                   FROM sqlitestudio_temp_table;
DROP TABLE sqlitestudio_temp_table;

ALTER TABLE pharm_group RENAME TO sqlitestudio_temp_table;
CREATE TABLE pharm_group (
    _id     INTEGER PRIMARY KEY AUTOINCREMENT,
    content TEXT
);
INSERT INTO pharm_group (
                             _id,
                             content
                         )
                         SELECT _id,
                                content
                           FROM sqlitestudio_temp_table;
ALTER TABLE drug RENAME TO sqlitestudio_temp_table0;
CREATE TABLE drug (
    _id             INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT    NOT NULL,
    firm_id         INTEGER,
    pharm_group_id  INTEGER REFERENCES pharm_group (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    pharm_action_id INTEGER REFERENCES pharm_action (_id) ON DELETE CASCADE
                                                          ON UPDATE CASCADE,
    usage_id        INTEGER REFERENCES usage (_id) ON DELETE CASCADE
                                                   ON UPDATE CASCADE,
    dosage_id       INTEGER REFERENCES dosage (_id) ON DELETE CASCADE
                                                    ON UPDATE CASCADE,
    side_effect_id  INTEGER REFERENCES side_effect (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    contras_id      INTEGER REFERENCES contras (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    special_id      INTEGER REFERENCES special (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    overdose_id     INTEGER REFERENCES overdose (_id) ON DELETE CASCADE
                                                      ON UPDATE CASCADE,
    interaction_id  INTEGER REFERENCES interaction (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    storage_id      INTEGER REFERENCES storage (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    composition_id  INTEGER REFERENCES composition (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE
);
INSERT INTO drug (
                     _id,
                     name,
                     firm_id,
                     pharm_group_id,
                     pharm_action_id,
                     usage_id,
                     dosage_id,
                     side_effect_id,
                     contras_id,
                     special_id,
                     overdose_id,
                     interaction_id,
                     storage_id,
                     composition_id
                 )
                 SELECT _id,
                        name,
                        firm_id,
                        pharm_group_id,
                        pharm_action_id,
                        usage_id,
                        dosage_id,
                        side_effect_id,
                        contras_id,
                        special_id,
                        overdose_id,
                        interaction_id,
                        storage_id,
                        composition_id
                   FROM sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table;

ALTER TABLE pharm_action RENAME TO sqlitestudio_temp_table;
CREATE TABLE pharm_action (
    _id     INTEGER PRIMARY KEY AUTOINCREMENT,
    content TEXT
);
INSERT INTO pharm_action (
                             _id,
                             content
                         )
                         SELECT _id,
                                content
                           FROM sqlitestudio_temp_table;
ALTER TABLE drug RENAME TO sqlitestudio_temp_table0;
CREATE TABLE drug (
    _id             INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT    NOT NULL,
    firm_id         INTEGER,
    pharm_group_id  INTEGER REFERENCES pharm_group (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    pharm_action_id INTEGER REFERENCES pharm_action (_id) ON DELETE CASCADE
                                                          ON UPDATE CASCADE,
    usage_id        INTEGER REFERENCES usage (_id) ON DELETE CASCADE
                                                   ON UPDATE CASCADE,
    dosage_id       INTEGER REFERENCES dosage (_id) ON DELETE CASCADE
                                                    ON UPDATE CASCADE,
    side_effect_id  INTEGER REFERENCES side_effect (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    contras_id      INTEGER REFERENCES contras (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    special_id      INTEGER REFERENCES special (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    overdose_id     INTEGER REFERENCES overdose (_id) ON DELETE CASCADE
                                                      ON UPDATE CASCADE,
    interaction_id  INTEGER REFERENCES interaction (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    storage_id      INTEGER REFERENCES storage (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    composition_id  INTEGER REFERENCES composition (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE
);
INSERT INTO drug (
                     _id,
                     name,
                     firm_id,
                     pharm_group_id,
                     pharm_action_id,
                     usage_id,
                     dosage_id,
                     side_effect_id,
                     contras_id,
                     special_id,
                     overdose_id,
                     interaction_id,
                     storage_id,
                     composition_id
                 )
                 SELECT _id,
                        name,
                        firm_id,
                        pharm_group_id,
                        pharm_action_id,
                        usage_id,
                        dosage_id,
                        side_effect_id,
                        contras_id,
                        special_id,
                        overdose_id,
                        interaction_id,
                        storage_id,
                        composition_id
                   FROM sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table;


ALTER TABLE usage RENAME TO sqlitestudio_temp_table;
CREATE TABLE usage (
    _id     INTEGER PRIMARY KEY AUTOINCREMENT,
    content TEXT
);
INSERT INTO usage (
                             _id,
                             content
                         )
                         SELECT _id,
                                content
                           FROM sqlitestudio_temp_table;
ALTER TABLE drug RENAME TO sqlitestudio_temp_table0;
CREATE TABLE drug (
    _id             INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT    NOT NULL,
    firm_id         INTEGER,
    pharm_group_id  INTEGER REFERENCES pharm_group (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    pharm_action_id INTEGER REFERENCES pharm_action (_id) ON DELETE CASCADE
                                                          ON UPDATE CASCADE,
    usage_id        INTEGER REFERENCES usage (_id) ON DELETE CASCADE
                                                   ON UPDATE CASCADE,
    dosage_id       INTEGER REFERENCES dosage (_id) ON DELETE CASCADE
                                                    ON UPDATE CASCADE,
    side_effect_id  INTEGER REFERENCES side_effect (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    contras_id      INTEGER REFERENCES contras (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    special_id      INTEGER REFERENCES special (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    overdose_id     INTEGER REFERENCES overdose (_id) ON DELETE CASCADE
                                                      ON UPDATE CASCADE,
    interaction_id  INTEGER REFERENCES interaction (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    storage_id      INTEGER REFERENCES storage (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    composition_id  INTEGER REFERENCES composition (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE
);
INSERT INTO drug (
                     _id,
                     name,
                     firm_id,
                     pharm_group_id,
                     pharm_action_id,
                     usage_id,
                     dosage_id,
                     side_effect_id,
                     contras_id,
                     special_id,
                     overdose_id,
                     interaction_id,
                     storage_id,
                     composition_id
                 )
                 SELECT _id,
                        name,
                        firm_id,
                        pharm_group_id,
                        pharm_action_id,
                        usage_id,
                        dosage_id,
                        side_effect_id,
                        contras_id,
                        special_id,
                        overdose_id,
                        interaction_id,
                        storage_id,
                        composition_id
                   FROM sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table;


ALTER TABLE dosage RENAME TO sqlitestudio_temp_table;
CREATE TABLE dosage (
    _id     INTEGER PRIMARY KEY AUTOINCREMENT,
    content TEXT
);
INSERT INTO dosage (
                             _id,
                             content
                         )
                         SELECT _id,
                                content
                           FROM sqlitestudio_temp_table;
ALTER TABLE drug RENAME TO sqlitestudio_temp_table0;
CREATE TABLE drug (
    _id             INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT    NOT NULL,
    firm_id         INTEGER,
    pharm_group_id  INTEGER REFERENCES pharm_group (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    pharm_action_id INTEGER REFERENCES pharm_action (_id) ON DELETE CASCADE
                                                          ON UPDATE CASCADE,
    usage_id        INTEGER REFERENCES usage (_id) ON DELETE CASCADE
                                                   ON UPDATE CASCADE,
    dosage_id       INTEGER REFERENCES dosage (_id) ON DELETE CASCADE
                                                    ON UPDATE CASCADE,
    side_effect_id  INTEGER REFERENCES side_effect (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    contras_id      INTEGER REFERENCES contras (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    special_id      INTEGER REFERENCES special (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    overdose_id     INTEGER REFERENCES overdose (_id) ON DELETE CASCADE
                                                      ON UPDATE CASCADE,
    interaction_id  INTEGER REFERENCES interaction (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    storage_id      INTEGER REFERENCES storage (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    composition_id  INTEGER REFERENCES composition (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE
);
INSERT INTO drug (
                     _id,
                     name,
                     firm_id,
                     pharm_group_id,
                     pharm_action_id,
                     usage_id,
                     dosage_id,
                     side_effect_id,
                     contras_id,
                     special_id,
                     overdose_id,
                     interaction_id,
                     storage_id,
                     composition_id
                 )
                 SELECT _id,
                        name,
                        firm_id,
                        pharm_group_id,
                        pharm_action_id,
                        usage_id,
                        dosage_id,
                        side_effect_id,
                        contras_id,
                        special_id,
                        overdose_id,
                        interaction_id,
                        storage_id,
                        composition_id
                   FROM sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table;


ALTER TABLE side_effect RENAME TO sqlitestudio_temp_table;
CREATE TABLE side_effect (
    _id     INTEGER PRIMARY KEY AUTOINCREMENT,
    content TEXT
);
INSERT INTO side_effect (
                             _id,
                             content
                         )
                         SELECT _id,
                                content
                           FROM sqlitestudio_temp_table;
ALTER TABLE drug RENAME TO sqlitestudio_temp_table0;
CREATE TABLE drug (
    _id             INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT    NOT NULL,
    firm_id         INTEGER,
    pharm_group_id  INTEGER REFERENCES pharm_group (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    pharm_action_id INTEGER REFERENCES pharm_action (_id) ON DELETE CASCADE
                                                          ON UPDATE CASCADE,
    usage_id        INTEGER REFERENCES usage (_id) ON DELETE CASCADE
                                                   ON UPDATE CASCADE,
    dosage_id       INTEGER REFERENCES dosage (_id) ON DELETE CASCADE
                                                    ON UPDATE CASCADE,
    side_effect_id  INTEGER REFERENCES side_effect (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    contras_id      INTEGER REFERENCES contras (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    special_id      INTEGER REFERENCES special (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    overdose_id     INTEGER REFERENCES overdose (_id) ON DELETE CASCADE
                                                      ON UPDATE CASCADE,
    interaction_id  INTEGER REFERENCES interaction (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    storage_id      INTEGER REFERENCES storage (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    composition_id  INTEGER REFERENCES composition (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE
);
INSERT INTO drug (
                     _id,
                     name,
                     firm_id,
                     pharm_group_id,
                     pharm_action_id,
                     usage_id,
                     dosage_id,
                     side_effect_id,
                     contras_id,
                     special_id,
                     overdose_id,
                     interaction_id,
                     storage_id,
                     composition_id
                 )
                 SELECT _id,
                        name,
                        firm_id,
                        pharm_group_id,
                        pharm_action_id,
                        usage_id,
                        dosage_id,
                        side_effect_id,
                        contras_id,
                        special_id,
                        overdose_id,
                        interaction_id,
                        storage_id,
                        composition_id
                   FROM sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table;


ALTER TABLE contras RENAME TO sqlitestudio_temp_table;
CREATE TABLE contras (
    _id     INTEGER PRIMARY KEY AUTOINCREMENT,
    content TEXT
);
INSERT INTO contras (
                             _id,
                             content
                         )
                         SELECT _id,
                                content
                           FROM sqlitestudio_temp_table;
ALTER TABLE drug RENAME TO sqlitestudio_temp_table0;
CREATE TABLE drug (
    _id             INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT    NOT NULL,
    firm_id         INTEGER,
    pharm_group_id  INTEGER REFERENCES pharm_group (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    pharm_action_id INTEGER REFERENCES pharm_action (_id) ON DELETE CASCADE
                                                          ON UPDATE CASCADE,
    usage_id        INTEGER REFERENCES usage (_id) ON DELETE CASCADE
                                                   ON UPDATE CASCADE,
    dosage_id       INTEGER REFERENCES dosage (_id) ON DELETE CASCADE
                                                    ON UPDATE CASCADE,
    side_effect_id  INTEGER REFERENCES side_effect (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    contras_id      INTEGER REFERENCES contras (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    special_id      INTEGER REFERENCES special (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    overdose_id     INTEGER REFERENCES overdose (_id) ON DELETE CASCADE
                                                      ON UPDATE CASCADE,
    interaction_id  INTEGER REFERENCES interaction (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    storage_id      INTEGER REFERENCES storage (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    composition_id  INTEGER REFERENCES composition (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE
);
INSERT INTO drug (
                     _id,
                     name,
                     firm_id,
                     pharm_group_id,
                     pharm_action_id,
                     usage_id,
                     dosage_id,
                     side_effect_id,
                     contras_id,
                     special_id,
                     overdose_id,
                     interaction_id,
                     storage_id,
                     composition_id
                 )
                 SELECT _id,
                        name,
                        firm_id,
                        pharm_group_id,
                        pharm_action_id,
                        usage_id,
                        dosage_id,
                        side_effect_id,
                        contras_id,
                        special_id,
                        overdose_id,
                        interaction_id,
                        storage_id,
                        composition_id
                   FROM sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table;


ALTER TABLE special RENAME TO sqlitestudio_temp_table;
CREATE TABLE special (
    _id     INTEGER PRIMARY KEY AUTOINCREMENT,
    content TEXT
);
INSERT INTO special (
                             _id,
                             content
                         )
                         SELECT _id,
                                content
                           FROM sqlitestudio_temp_table;
ALTER TABLE drug RENAME TO sqlitestudio_temp_table0;
CREATE TABLE drug (
    _id             INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT    NOT NULL,
    firm_id         INTEGER,
    pharm_group_id  INTEGER REFERENCES pharm_group (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    pharm_action_id INTEGER REFERENCES pharm_action (_id) ON DELETE CASCADE
                                                          ON UPDATE CASCADE,
    usage_id        INTEGER REFERENCES usage (_id) ON DELETE CASCADE
                                                   ON UPDATE CASCADE,
    dosage_id       INTEGER REFERENCES dosage (_id) ON DELETE CASCADE
                                                    ON UPDATE CASCADE,
    side_effect_id  INTEGER REFERENCES side_effect (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    contras_id      INTEGER REFERENCES contras (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    special_id      INTEGER REFERENCES special (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    overdose_id     INTEGER REFERENCES overdose (_id) ON DELETE CASCADE
                                                      ON UPDATE CASCADE,
    interaction_id  INTEGER REFERENCES interaction (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    storage_id      INTEGER REFERENCES storage (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    composition_id  INTEGER REFERENCES composition (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE
);
INSERT INTO drug (
                     _id,
                     name,
                     firm_id,
                     pharm_group_id,
                     pharm_action_id,
                     usage_id,
                     dosage_id,
                     side_effect_id,
                     contras_id,
                     special_id,
                     overdose_id,
                     interaction_id,
                     storage_id,
                     composition_id
                 )
                 SELECT _id,
                        name,
                        firm_id,
                        pharm_group_id,
                        pharm_action_id,
                        usage_id,
                        dosage_id,
                        side_effect_id,
                        contras_id,
                        special_id,
                        overdose_id,
                        interaction_id,
                        storage_id,
                        composition_id
                   FROM sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table;


ALTER TABLE overdose RENAME TO sqlitestudio_temp_table;
CREATE TABLE overdose (
    _id     INTEGER PRIMARY KEY AUTOINCREMENT,
    content TEXT
);
INSERT INTO overdose (
                             _id,
                             content
                         )
                         SELECT _id,
                                content
                           FROM sqlitestudio_temp_table;
ALTER TABLE drug RENAME TO sqlitestudio_temp_table0;
CREATE TABLE drug (
    _id             INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT    NOT NULL,
    firm_id         INTEGER,
    pharm_group_id  INTEGER REFERENCES pharm_group (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    pharm_action_id INTEGER REFERENCES pharm_action (_id) ON DELETE CASCADE
                                                          ON UPDATE CASCADE,
    usage_id        INTEGER REFERENCES usage (_id) ON DELETE CASCADE
                                                   ON UPDATE CASCADE,
    dosage_id       INTEGER REFERENCES dosage (_id) ON DELETE CASCADE
                                                    ON UPDATE CASCADE,
    side_effect_id  INTEGER REFERENCES side_effect (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    contras_id      INTEGER REFERENCES contras (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    special_id      INTEGER REFERENCES special (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    overdose_id     INTEGER REFERENCES overdose (_id) ON DELETE CASCADE
                                                      ON UPDATE CASCADE,
    interaction_id  INTEGER REFERENCES interaction (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    storage_id      INTEGER REFERENCES storage (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    composition_id  INTEGER REFERENCES composition (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE
);
INSERT INTO drug (
                     _id,
                     name,
                     firm_id,
                     pharm_group_id,
                     pharm_action_id,
                     usage_id,
                     dosage_id,
                     side_effect_id,
                     contras_id,
                     special_id,
                     overdose_id,
                     interaction_id,
                     storage_id,
                     composition_id
                 )
                 SELECT _id,
                        name,
                        firm_id,
                        pharm_group_id,
                        pharm_action_id,
                        usage_id,
                        dosage_id,
                        side_effect_id,
                        contras_id,
                        special_id,
                        overdose_id,
                        interaction_id,
                        storage_id,
                        composition_id
                   FROM sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table;


ALTER TABLE interaction RENAME TO sqlitestudio_temp_table;
CREATE TABLE interaction (
    _id     INTEGER PRIMARY KEY AUTOINCREMENT,
    content TEXT
);
INSERT INTO interaction (
                             _id,
                             content
                         )
                         SELECT _id,
                                content
                           FROM sqlitestudio_temp_table;
ALTER TABLE drug RENAME TO sqlitestudio_temp_table0;
CREATE TABLE drug (
    _id             INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT    NOT NULL,
    firm_id         INTEGER,
    pharm_group_id  INTEGER REFERENCES pharm_group (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    pharm_action_id INTEGER REFERENCES pharm_action (_id) ON DELETE CASCADE
                                                          ON UPDATE CASCADE,
    usage_id        INTEGER REFERENCES usage (_id) ON DELETE CASCADE
                                                   ON UPDATE CASCADE,
    dosage_id       INTEGER REFERENCES dosage (_id) ON DELETE CASCADE
                                                    ON UPDATE CASCADE,
    side_effect_id  INTEGER REFERENCES side_effect (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    contras_id      INTEGER REFERENCES contras (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    special_id      INTEGER REFERENCES special (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    overdose_id     INTEGER REFERENCES overdose (_id) ON DELETE CASCADE
                                                      ON UPDATE CASCADE,
    interaction_id  INTEGER REFERENCES interaction (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    storage_id      INTEGER REFERENCES storage (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    composition_id  INTEGER REFERENCES composition (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE
);
INSERT INTO drug (
                     _id,
                     name,
                     firm_id,
                     pharm_group_id,
                     pharm_action_id,
                     usage_id,
                     dosage_id,
                     side_effect_id,
                     contras_id,
                     special_id,
                     overdose_id,
                     interaction_id,
                     storage_id,
                     composition_id
                 )
                 SELECT _id,
                        name,
                        firm_id,
                        pharm_group_id,
                        pharm_action_id,
                        usage_id,
                        dosage_id,
                        side_effect_id,
                        contras_id,
                        special_id,
                        overdose_id,
                        interaction_id,
                        storage_id,
                        composition_id
                   FROM sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table;


ALTER TABLE storage RENAME TO sqlitestudio_temp_table;
CREATE TABLE storage (
    _id     INTEGER PRIMARY KEY AUTOINCREMENT,
    content TEXT
);
INSERT INTO storage (
                             _id,
                             content
                         )
                         SELECT _id,
                                content
                           FROM sqlitestudio_temp_table;
ALTER TABLE drug RENAME TO sqlitestudio_temp_table0;
CREATE TABLE drug (
    _id             INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT    NOT NULL,
    firm_id         INTEGER,
    pharm_group_id  INTEGER REFERENCES pharm_group (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    pharm_action_id INTEGER REFERENCES pharm_action (_id) ON DELETE CASCADE
                                                          ON UPDATE CASCADE,
    usage_id        INTEGER REFERENCES usage (_id) ON DELETE CASCADE
                                                   ON UPDATE CASCADE,
    dosage_id       INTEGER REFERENCES dosage (_id) ON DELETE CASCADE
                                                    ON UPDATE CASCADE,
    side_effect_id  INTEGER REFERENCES side_effect (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    contras_id      INTEGER REFERENCES contras (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    special_id      INTEGER REFERENCES special (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    overdose_id     INTEGER REFERENCES overdose (_id) ON DELETE CASCADE
                                                      ON UPDATE CASCADE,
    interaction_id  INTEGER REFERENCES interaction (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    storage_id      INTEGER REFERENCES storage (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    composition_id  INTEGER REFERENCES composition (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE
);
INSERT INTO drug (
                     _id,
                     name,
                     firm_id,
                     pharm_group_id,
                     pharm_action_id,
                     usage_id,
                     dosage_id,
                     side_effect_id,
                     contras_id,
                     special_id,
                     overdose_id,
                     interaction_id,
                     storage_id,
                     composition_id
                 )
                 SELECT _id,
                        name,
                        firm_id,
                        pharm_group_id,
                        pharm_action_id,
                        usage_id,
                        dosage_id,
                        side_effect_id,
                        contras_id,
                        special_id,
                        overdose_id,
                        interaction_id,
                        storage_id,
                        composition_id
                   FROM sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table;


ALTER TABLE composition RENAME TO sqlitestudio_temp_table;
CREATE TABLE composition (
    _id     INTEGER PRIMARY KEY AUTOINCREMENT,
    content TEXT
);
INSERT INTO composition (
                             _id,
                             content
                         )
                         SELECT _id,
                                content
                           FROM sqlitestudio_temp_table;
ALTER TABLE drug RENAME TO sqlitestudio_temp_table0;
CREATE TABLE drug (
    _id             INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT    NOT NULL,
    firm_id         INTEGER,
    pharm_group_id  INTEGER REFERENCES pharm_group (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    pharm_action_id INTEGER REFERENCES pharm_action (_id) ON DELETE CASCADE
                                                          ON UPDATE CASCADE,
    usage_id        INTEGER REFERENCES usage (_id) ON DELETE CASCADE
                                                   ON UPDATE CASCADE,
    dosage_id       INTEGER REFERENCES dosage (_id) ON DELETE CASCADE
                                                    ON UPDATE CASCADE,
    side_effect_id  INTEGER REFERENCES side_effect (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    contras_id      INTEGER REFERENCES contras (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    special_id      INTEGER REFERENCES special (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    overdose_id     INTEGER REFERENCES overdose (_id) ON DELETE CASCADE
                                                      ON UPDATE CASCADE,
    interaction_id  INTEGER REFERENCES interaction (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    storage_id      INTEGER REFERENCES storage (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    composition_id  INTEGER REFERENCES composition (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE
);
INSERT INTO drug (
                     _id,
                     name,
                     firm_id,
                     pharm_group_id,
                     pharm_action_id,
                     usage_id,
                     dosage_id,
                     side_effect_id,
                     contras_id,
                     special_id,
                     overdose_id,
                     interaction_id,
                     storage_id,
                     composition_id
                 )
                 SELECT _id,
                        name,
                        firm_id,
                        pharm_group_id,
                        pharm_action_id,
                        usage_id,
                        dosage_id,
                        side_effect_id,
                        contras_id,
                        special_id,
                        overdose_id,
                        interaction_id,
                        storage_id,
                        composition_id
                   FROM sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table0;
DROP TABLE sqlitestudio_temp_table;

ALTER TABLE drug RENAME TO sqlitestudio_temp_table;

CREATE TABLE drug (
    _id             INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT    NOT NULL,
    firm_id         INTEGER REFERENCES firm (_id) ON DELETE CASCADE
                                                  ON UPDATE CASCADE,
    pharm_group_id  INTEGER REFERENCES pharm_group (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    pharm_action_id INTEGER REFERENCES pharm_action (_id) ON DELETE CASCADE
                                                          ON UPDATE CASCADE,
    usage_id        INTEGER REFERENCES usage (_id) ON DELETE CASCADE
                                                   ON UPDATE CASCADE,
    dosage_id       INTEGER REFERENCES dosage (_id) ON DELETE CASCADE
                                                    ON UPDATE CASCADE,
    side_effect_id  INTEGER REFERENCES side_effect (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    contras_id      INTEGER REFERENCES contras (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    special_id      INTEGER REFERENCES special (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    overdose_id     INTEGER REFERENCES overdose (_id) ON DELETE CASCADE
                                                      ON UPDATE CASCADE,
    interaction_id  INTEGER REFERENCES interaction (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
    storage_id      INTEGER REFERENCES storage (_id) ON DELETE CASCADE
                                                     ON UPDATE CASCADE,
    composition_id  INTEGER REFERENCES composition (_id) ON DELETE CASCADE
                                                         ON UPDATE CASCADE
);

INSERT INTO drug (
                     _id,
                     name,
                     firm_id,
                     pharm_group_id,
                     pharm_action_id,
                     usage_id,
                     dosage_id,
                     side_effect_id,
                     contras_id,
                     special_id,
                     overdose_id,
                     interaction_id,
                     storage_id,
                     composition_id
                 )
                 SELECT _id,
                        name,
                        firm_id,
                        pharm_group_id,
                        pharm_action_id,
                        usage_id,
                        dosage_id,
                        side_effect_id,
                        contras_id,
                        special_id,
                        overdose_id,
                        interaction_id,
                        storage_id,
                        composition_id
                   FROM sqlitestudio_temp_table;

DROP TABLE sqlitestudio_temp_table;
CREATE INDEX drug_name ON drug (
    name
);

DROP TABLE drugs;
DROP TABLE firms;
DROP TABLE content;

CREATE TABLE 'course' (
    '_id'      INTEGER PRIMARY KEY AUTOINCREMENT,
    'drug_id'  INTEGER REFERENCES 'drug' ('_id') ON DELETE CASCADE
                                           ON UPDATE CASCADE,
    'start'    INTEGER,
    'end'    INTEGER,
    'interval' INTEGER
);

CREATE TABLE 'taking_time' (
    '_id'       INTEGER PRIMARY KEY AUTOINCREMENT,
    'time'      INTEGER,
    'course_id' INTEGER REFERENCES 'course' ('_id') ON DELETE CASCADE
                                              ON UPDATE CASCADE
);


vacuum;
