INSERT INTO about_page
(
    id,
    hero_title,
    hero_subtitle,
    mission_heading,
    mission_body1,
    mission_body2,
    tag_year,
    tag_text,
    team_title
)
VALUES
    (
        1,
        'About PathFinder',
        'We believe every South African learner deserves expert career guidance — regardless of their school, province, or background.',
        'Our Mission',
        'PathFinder was born from a simple observation: thousands of South African Grade 12 learners make life-altering university application decisions without adequate guidance. Many apply for courses they do not qualify for. Others settle for less because they do not know they qualify for more.',
        'Our platform bridges that gap — giving every learner access to the kind of personalised career guidance previously available only to students at well-resourced schools with professional guidance counsellors.',
        '2021',
        'Founded in Gauteng',
        'Meet the Team'
    );

INSERT INTO about_metrics
(
    metric_key,
    title,
    description,
    icon,
    display_order,
    about_page_id
)
VALUES
    (
        'PROVINCES',
        'Provinces',
        'We serve learners across every South African province, from urban centres to rural communities.',
        '🌍',
        1,
        1
    );

INSERT INTO about_metrics
(
    metric_key,
    title,
    description,
    icon,
    display_order,
    about_page_id
)
VALUES
    (
        'PROGRAMMES',
        'Programmes',
        'Covering every major study area at all 26 public universities in South Africa.',
        '🎓',
        2,
        1
    );

INSERT INTO about_metrics
(
    metric_key,
    title,
    description,
    icon,
    display_order,
    about_page_id
)
VALUES
    (
        'STUDENTS',
        'Students',
        'Thousands of learners have used PathFinder to make confident, informed university decisions.',
        '👥',
        3,
        1
    );

INSERT INTO team_member
(
    full_name,
    position,
    biography,
    initials,
    image_url,
    display_order,
    about_page_id
)
VALUES
    (
        'Dr. Okeke Destiney',
        'Founder & CEO',
        'Former DHET Education Specialist',
        'ZM',
        NULL,
        1,
        1
    );

INSERT INTO team_member
(
    full_name,
    position,
    biography,
    initials,
    image_url,
    display_order,
    about_page_id
)
VALUES
    (
        'Emanuel Mafalo',
        'CTO',
        'UCT Computer Science Alumni',
        'RP',
        NULL,
        2,
        1
    );

INSERT INTO team_member
(
    full_name,
    position,
    biography,
    initials,
    image_url,
    display_order,
    about_page_id
)
VALUES
    (
        'Gift Tendie',
        'Head of Curriculum',
        'Wits Education Faculty PhD',
        'FH',
        NULL,
        3,
        1
    );



INSERT INTO contact_info
(
    id,
    support_email,
    support_phone,
    office_location
)
VALUES
    (
        1,
        'support@pathfinder.co.za',
        '0800 PATH FIND',
        '124 BlackHealth, Johannesburg, Gauteng, South Africa'
    );