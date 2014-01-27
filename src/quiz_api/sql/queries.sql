-- name: quiz-by-pk
-- Direct quiz lookup
SELECT *
FROM quiz_live
WHERE id = ?

-- name: questions-for-quiz
-- Lookup all the questions-for-quiz
Select *
FROM quiz_question_live
WHERE quiz_question_live.quiz_id = ?
ORDER BY sort_order

-- name: bands-for-quiz
-- Lookup all the bands for the quiz
SELECT * 
FROM quiz_band_live
WHERE quiz_id = ?
ORDER BY sort_order

--name: find-all-answers
--Lookup all the answers by question id
SELECT * 
FROM quiz_answer_live
WHERE question_id = ?
ORDER BY sort_order